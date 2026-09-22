package ru.otus.server;

import ru.otus.exception.AuthenticationException;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRegistryImpl implements SessionRegistry {

    private final Map<String, ClientSession> sessions = new ConcurrentHashMap<>();

    @Override
    public ClientSession add(String name, UserRole role, DataOutputStream out, Socket socket) {
        ClientSession session = ClientSession.builder()
                .name(name)
                .role(role)
                .out(out)
                .socket(socket)
                .build();
        if (Objects.nonNull(sessions.putIfAbsent(name, session))) {
            throw new AuthenticationException("%s has already logged in".formatted(name));
        }
        return session;
    }

    @Override
    public ClientSession get(String name) {
        return sessions.get(name);
    }

    @Override
    public List<ClientSession> getSessions() {
        return sessions.values().stream().toList();
    }

    @Override
    public boolean remove(String name, ClientSession session) {
        if (name == null || session == null) {
            return false;
        }
        return sessions.remove(name, session);
    }
}
