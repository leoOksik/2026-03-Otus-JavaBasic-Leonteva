package ru.otus.java.basic.homework4.user;

import java.time.Year;

public class User {

    private final String lastName;
    private final String firstName;
    private final String patronymic;
    private final Year birthYear;
    private final String email;

    User(String lastName, String firstName, String patronymic, Year birthYear, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthYear = birthYear;
        this.email = email;
    }

    @Override
    public String toString() {
        return """
                ФИО: %s %s %s
                Год рождения: %s
                e-mail: %s
                """
                .formatted(lastName, firstName, patronymic, birthYear, email);
    }

    public Year getBirthYear() {
        return birthYear;
    }
}
