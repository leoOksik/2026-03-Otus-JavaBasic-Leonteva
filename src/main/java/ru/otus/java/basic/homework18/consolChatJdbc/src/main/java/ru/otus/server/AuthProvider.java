package ru.otus.server;

import ru.otus.server.database.entity.User;

public interface AuthProvider {
    User register(String name, String login, String password);

    User authenticate(String login, String password);
}
