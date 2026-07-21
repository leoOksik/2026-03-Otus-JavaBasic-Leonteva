package ru.otus.java.basic.homework15.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {

    private static final Pattern PATTERN = Pattern.compile("^/w\\s+(\\S+)\\s+(.+)$");

    private final Socket socket;
    private final Map<String, DataOutputStream> clients;
    private final DataInputStream in;
    private final DataOutputStream out;

    public ClientHandler(Socket socket, Map<String, DataOutputStream> clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String name = null;
        try {
            while (true) {
                name = in.readUTF().trim().toLowerCase();

                if (!name.matches("\\S+")) {
                    out.writeUTF("Name must be single word without spaces");
                } else if (clients.putIfAbsent(name, out) != null) {
                    out.writeUTF("Name %s exists".formatted(name));
                } else {
                    break;
                }
            }
            out.writeUTF("Name was saved: %s".formatted(name));

            while (true) {
                String message = in.readUTF();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
                sendMessage(name, message);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (name != null) {
                clients.remove(name, out);
            }
            close();
        }
    }

    private void sendMessage(String sender, String message) throws IOException {
        Matcher matcher = PATTERN.matcher(message);

        if (!matcher.matches()) {
            out.writeUTF("Incorrect input. Format: /w name message");
            return;
        }
        String nickName = matcher.group(1).toLowerCase();
        String text = matcher.group(2);

        DataOutputStream dosClient = clients.get(nickName);
        if (dosClient == null) {
            out.writeUTF("Client %s not found".formatted(nickName));
            return;
        }
        dosClient.writeUTF("%s: %s".formatted(sender, text));
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
