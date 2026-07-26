package ru.otus.server;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public enum Command {
    AUTH("/auth\\s+(\\S+)\\s+(\\S+)", false),
    REG("/reg\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)", false),
    PRIVATE("/w\\s+(\\S+)\\s+(.+)", false),
    KICK("/kick\\s+(\\S+)", true),
    EXIT("/exit", false);

    @Getter(AccessLevel.NONE)
    private final Pattern pattern;
    private final boolean adminCommand;

    Command(String regex, boolean adminCommand) {
        this.pattern = Pattern.compile(regex);
        this.adminCommand = adminCommand;
    }

    public static ParsedCommand parse(String message) {
        for (Command command : values()) {
            Matcher matcher = command.pattern.matcher(message);
            if (matcher.matches()) {
                return new ParsedCommand(command, matcher);
            }
        }
        return null;
    }
}
