package ru.otus.java.basic.homework4.user;

import java.time.Year;

public class Homework4 {
    public static void main(String[] args) {

        User[] users = new User[10];

        for (int i = 1, j = 0; i <= users.length; i++, j += 5) {

            User user1 = new User(
                    "Иванов" + i, "Ивaн" + i, "Иванович" + i,
                    Year.of(1970).plusYears(j), "ivanov%d@yandex.ru".formatted(i));

            users[i - 1] = user1;
        }

        for (User user : users) {
            if (user.getBirthYear().isBefore(Year.now().minusYears(40))) {
                System.out.println(user);
            }
        }
    }
}
