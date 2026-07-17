package ru.otus.java.basic.homework15.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ClientServiceImpl implements ClientService {

    private final String host;
    private final int port;

    public ClientServiceImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start(Scanner input) {
        try (Socket socket = new Socket(host, port);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            CompletableFuture.runAsync(() -> readMessage(in));

            System.out.println("Enter your name:");
            out.writeUTF(input.nextLine());

            while (true) {
                String message = input.nextLine();
                out.writeUTF(message);
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void readMessage(DataInputStream in) {
        try {
            while (true) {
                System.out.println(in.readUTF());
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
