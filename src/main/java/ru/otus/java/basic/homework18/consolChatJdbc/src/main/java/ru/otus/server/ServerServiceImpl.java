package ru.otus.server;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.server.database.config.DataSourceConfig;
import ru.otus.server.database.DbConnection;
import ru.otus.server.database.security.BCryptPasswordHasher;
import ru.otus.server.database.security.PasswordHasher;
import ru.otus.server.database.service.UserServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ExecutorService executorServicePool;
    private final int port;
    private final AuthProvider authProvider;
    private final SessionRegistry sessionRegistry;
    private final DbConnection db;
    PasswordHasher passwordHasher;

    public ServerServiceImpl(int port) {
        this.port = port;
        this.executorServicePool = Executors.newVirtualThreadPerTaskExecutor();
        this.sessionRegistry = new SessionRegistryImpl();
        this.db = new DbConnection(DataSourceConfig.createDataSource());
        migrate();
        this.passwordHasher = new BCryptPasswordHasher();
        this.authProvider = new AuthProviderImpl(new UserServiceImpl(db, passwordHasher));
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
            db.close();
        }
    }

    private void migrate() {
        try (Connection conn = db.getConnection()) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new Liquibase(
                    "db/changelog/db.changelog-master.yaml",
                    new ClassLoaderResourceAccessor(),
                    database);
            liquibase.update();
            log.info("Liquibase migration completed");
        } catch (Exception e) {
            log.error("Liquibase migration failed", e);
            throw new IllegalStateException("Migration failed");
        }
    }
}
