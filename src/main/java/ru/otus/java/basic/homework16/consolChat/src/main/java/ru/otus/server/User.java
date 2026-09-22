package ru.otus.server;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private final String name;
    private final String login;
    private final String password;

    @Builder.Default
    private final UserRole role = UserRole.USER;
}
