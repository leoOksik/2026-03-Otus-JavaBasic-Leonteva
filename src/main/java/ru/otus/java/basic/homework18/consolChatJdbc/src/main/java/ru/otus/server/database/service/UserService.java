package ru.otus.server.database.service;

import ru.otus.server.database.entity.User;

public interface UserService {

    User register(String name, String email, String password);

    User login(String email, String password);

    boolean isAdmin(String email);
}
