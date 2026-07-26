package ru.otus.client;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
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

            Thread reader = Thread.ofVirtual().start(() -> readMessage(in));

            while (true) {
                String message = input.nextLine();
                out.writeUTF(message);
                if ("/exit".equalsIgnoreCase(message)) {
                    break;
                }
            }
            reader.interrupt();
        } catch (IOException ex) {
            System.out.println("Connection closed");
        }
    }

    private void readMessage(DataInputStream in) {
        try {
            while (true) {
                System.out.println(in.readUTF());
            }
        } catch (IOException ex) {
            System.out.println("Disconnected from server. Press Enter to exit.");
        }
    }
}
