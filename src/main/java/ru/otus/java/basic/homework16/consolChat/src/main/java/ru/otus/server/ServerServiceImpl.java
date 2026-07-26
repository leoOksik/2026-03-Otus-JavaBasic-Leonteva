package ru.otus.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ExecutorService executorServicePool;
    private final int port;
    private final AuthProvider authProvider;
    private final SessionRegistry sessionRegistry;

    public ServerServiceImpl(int port) {
        this.port = port;
        this.executorServicePool = Executors.newVirtualThreadPerTaskExecutor();
        this.sessionRegistry = new SessionRegistryImpl();
        this.authProvider = buildAuthProvider();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server started on port {}", port);

            while (true) {
                Socket socket = serverSocket.accept();
                log.info("Accepted connection, port = {}", socket.getPort());
                try {
                    executorServicePool.execute(new ClientHandler(socket, authProvider, sessionRegistry));
                } catch (IOException ex) {
                    log.error("Error init: {}", ex.getMessage(), ex);
                    socket.close();
                }
            }
        } catch (IOException ex) {
            log.error("Server error: {}", ex.getMessage(), ex);
        } finally {
            executorServicePool.shutdownNow();
        }
    }

    private AuthProvider buildAuthProvider() {
        UserAuthService userAuthService = new UserAuthServiceImpl();
        userAuthService.addAdmin("admin", "admin", "admin123");
        return new AuthProviderImpl(userAuthService);
    }
}
