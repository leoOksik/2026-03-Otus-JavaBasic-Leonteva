package ru.otus.server;

import ru.otus.exception.InvalidDataException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserAuthServiceImpl implements UserAuthService {

    private final Map<String, User> clients = new ConcurrentHashMap<>();

    @Override
    public synchronized User save(String name, String login, String password) {
        if (isLoginExist(login)) {
            throw new InvalidDataException("Login already exists");
        }
        if (isUserNameExists(name)) {
            throw new InvalidDataException("Username already exists");
        }
        User user = User.builder()
                .name(name)
                .login(login)
                .password(password)
                .build();
        clients.put(login, user);
        return user;
    }

    @Override
    public User checkLoginAndPassword(String login, String password) {
        User user = clients.get(login);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public void addAdmin(String name, String login, String password) {
        User admin = User.builder()
                .name(name)
                .login(login)
                .password(password)
                .role(UserRole.ADMIN)
                .build();
        clients.putIfAbsent(login, admin);
    }

    private boolean isUserNameExists(String name) {
        return clients.values().stream().anyMatch(u -> u.getName().equals(name));
    }

    private boolean isLoginExist(String login) {
        return clients.containsKey(login);
    }
}
