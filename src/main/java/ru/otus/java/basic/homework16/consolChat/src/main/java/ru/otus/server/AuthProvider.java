package ru.otus.server;

public interface AuthProvider {
    User register(String name, String login, String password);

    User authenticate(String login, String password);
}
