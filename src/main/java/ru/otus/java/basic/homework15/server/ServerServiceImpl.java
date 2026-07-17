package ru.otus.java.basic.homework15.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerServiceImpl implements ServerService {

    private final int port;
    private final Map<String, DataOutputStream> clients;

    public ServerServiceImpl(int port) {
        this.port = port;
        this.clients = new ConcurrentHashMap<>();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted connection from " + socket.getPort());
                try {
                    new Thread(new ClientHandler(socket, clients)).start();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
