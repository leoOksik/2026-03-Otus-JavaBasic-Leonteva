package ru.otus.server;

import lombok.RequiredArgsConstructor;
import ru.otus.exception.AuthenticationException;
import ru.otus.exception.InvalidDataException;

@RequiredArgsConstructor
public class AuthProviderImpl implements AuthProvider {

    private static final String NAME_LOGIN_PATTERN = "\\S{3,}";
    private static final String PASSWORD_PATTERN = "(?=.*\\d).{5,}";

    private final UserAuthService userAuthService;

    @Override
    public User register(String name, String login, String password) {
        if (!name.matches(NAME_LOGIN_PATTERN) || !login.matches(NAME_LOGIN_PATTERN)) {
            throw new InvalidDataException("Name and login must have 3 or more characters");
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new InvalidDataException("Password must have 5 or more characters and one digit");
        }
        return userAuthService.save(name, login, password);
    }

    @Override
    public User authenticate(String login, String password) {
        User user = userAuthService.checkLoginAndPassword(login, password);
        if (user == null) {
            throw new AuthenticationException("Wrong login or password");
        }
        return user;
    }
}
