package ru.otus.server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public interface SessionRegistry {
    ClientSession add(String name, UserRole role, DataOutputStream out, Socket socket);

    ClientSession get(String name);

    ClientSession remove(String name);

    List<ClientSession> getSessions();
}
