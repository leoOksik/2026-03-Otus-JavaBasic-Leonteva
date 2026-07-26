package ru.otus.server;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Getter
@Builder
public class ClientSession {
    private final String name;
    private final UserRole role;

    @Getter(AccessLevel.NONE)
    private final DataOutputStream out;

    @Getter(AccessLevel.NONE)
    private final Socket socket;

    public void send(String message) throws IOException {
        out.writeUTF(message);
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}
