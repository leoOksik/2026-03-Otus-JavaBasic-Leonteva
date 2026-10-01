package ru.otus.server;

import lombok.RequiredArgsConstructor;
import ru.otus.server.database.entity.User;
import ru.otus.exception.InvalidDataException;
import ru.otus.server.database.service.UserService;

@RequiredArgsConstructor
public class AuthProviderImpl implements AuthProvider {

    private static final String NAME_LOGIN_PATTERN = "\\S{3,}";

    private static final String PASSWORD_PATTERN = "(?=.*\\d).{5,}";

    private final UserService userService;

    @Override
    public User register(String name, String email, String password) {
        if (!name.matches(NAME_LOGIN_PATTERN) || !email.matches(NAME_LOGIN_PATTERN)) {
            throw new InvalidDataException("Name and login must have 3 or more characters");
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new InvalidDataException("Password must have 5 or more characters and one digit");
        }
        return userService.register(name, email, password);
    }

    @Override
    public User authenticate(String email, String password) {
        return userService.login(email, password);
    }
}
