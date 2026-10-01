package ru.otus.server.database.security;

public interface PasswordHasher {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
