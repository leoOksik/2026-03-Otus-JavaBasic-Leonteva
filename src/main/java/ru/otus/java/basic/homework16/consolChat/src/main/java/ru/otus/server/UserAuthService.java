package ru.otus.server;

public interface UserAuthService {
    User save(String name, String login, String password);

    User checkLoginAndPassword(String login, String password);

    void addAdmin(String name, String login, String password);
}
