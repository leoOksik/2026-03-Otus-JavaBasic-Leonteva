package ru.otus.server;

import lombok.extern.slf4j.Slf4j;
import ru.otus.exception.AuthenticationException;
import ru.otus.exception.InvalidDataException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Slf4j
public class ClientHandler implements Runnable {

    private static final String AUTH_COMMANDS = """
            Available commands:
            /auth login password
            /reg username login password
            /exit""";

    private static final String CHAT_COMMANDS = """
            Available commands:
            /w username message
            /exit""";

    private static final String KICK_COMMAND = "/kick username";

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final AuthProvider authProvider;
    private final SessionRegistry sessionRegistry;
    private ClientSession session;

    public ClientHandler(Socket socket, AuthProvider authProvider, SessionRegistry sessionRegistry) throws IOException {
        this.socket = socket;
        this.authProvider = authProvider;
        this.sessionRegistry = sessionRegistry;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            authLoop();
            if (session != null) {
                chatLoop();
            }
        } catch (IOException ex) {
            log.error("Connection error: {}", ex.getMessage());
        } finally {
            closeSession();
        }
    }

    private void authLoop() throws IOException {
        sendMessage(AUTH_COMMANDS);

        while (session == null) {
            String message = in.readUTF().trim();

            ParsedCommand parsed = Command.parse(message);
            if (parsed == null) {
                sendMessage(AUTH_COMMANDS);
                continue;
            }

            try {
                switch (parsed.command()) {
                    case AUTH -> {
                        User user = authProvider.authenticate(parsed.group(1), parsed.group(2));
                        session = sessionRegistry.add(user.getName(), user.getRole(), out, socket);
                        sendMessage("Auth successful: %s".formatted(session.getName()));
                    }
                    case REG -> {
                        User user = authProvider.register(
                                parsed.group(1), parsed.group(2), parsed.group(3));
                        sendMessage("Register successful: %s. Now login: /auth login password"
                                .formatted(user.getName()));
                    }
                    case EXIT -> {
                        return;
                    }
                    default -> sendMessage(AUTH_COMMANDS);
                }
            } catch (InvalidDataException | AuthenticationException ex) {
                sendMessage(ex.getMessage());
            }
        }
    }

    private void chatLoop() throws IOException {
        sendMessage(chatCommands());

        while (true) {
            String message = in.readUTF().trim();

            ParsedCommand parsed = Command.parse(message);
            if (parsed == null) {
                if (message.startsWith("/")) {
                    sendMessage(chatCommands());
                } else if (!message.isBlank()) {
                    broadcast(message);
                }
                continue;
            }

            Command command = parsed.command();
            if (command.isAdminCommand() && session.getRole() != UserRole.ADMIN) {
                sendMessage(chatCommands());
                continue;
            }

            switch (command) {
                case PRIVATE -> sendPrivateMessage(parsed.group(1), parsed.group(2));
                case KICK -> kickUser(parsed.group(1));
                case EXIT -> {
                    return;
                }
                default -> sendMessage(chatCommands());
            }
        }
    }

    private String chatCommands() {
        if (session.getRole() == UserRole.ADMIN) {
            return CHAT_COMMANDS + "\n" + KICK_COMMAND;
        }
        return CHAT_COMMANDS;
    }

    private void sendPrivateMessage(String name, String text) {
        ClientSession client = sessionRegistry.get(name);
        if (client == null) {
            sendMessage("User %s not found".formatted(name));
            return;
        }
        try {
            client.send("%s: %s".formatted(session.getName(), text));
        } catch (IOException ex) {
            log.error("Send error: {}", ex.getMessage());
            sendMessage("Message not delivered");
        }
    }

    private void kickUser(String name) {
        ClientSession client = sessionRegistry.remove(name);
        if (client == null) {
            sendMessage("User %s not found".formatted(name));
            return;
        }
        try {
            client.disconnect();
        } catch (IOException ex) {
            log.error("Disconnect error: {}", ex.getMessage());
        }
        sendMessage("User %s disconnected".formatted(name));
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException ex) {
            log.error("Send error: {}", ex.getMessage());
        }
    }

    private void broadcast(String message) {
        String senderName = session.getName();
        List<ClientSession> sessions = sessionRegistry.getSessions();
        for (ClientSession clientSession : sessions) {
            if (!clientSession.getName().equals(senderName)) {
                try {
                    clientSession.send("%s: %s".formatted(senderName, message));
                } catch (IOException ex) {
                    log.error("Broadcast error to {}: {}", clientSession.getName(), ex.getMessage());
                }
            }
        }
    }

    private void closeSession() {
        if (session != null) {
            sessionRegistry.remove(session.getName());
        }
        try {
            socket.close();
        } catch (IOException ex) {
            log.error("Close error: {}", ex.getMessage());
        }
    }
}
