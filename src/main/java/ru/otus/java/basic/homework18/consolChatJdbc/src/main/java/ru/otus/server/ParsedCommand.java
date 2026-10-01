package ru.otus.server;

import java.util.regex.Matcher;

public record ParsedCommand(Command command, Matcher matcher) {

    public String group(int index) {
        return matcher.group(index);
    }
}
