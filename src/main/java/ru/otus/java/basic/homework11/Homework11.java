package ru.otus.java.basic.homework11;

public class Homework11 {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook("Ирина Петрова", "79567567755");
        PhoneBook phoneBook2 = new PhoneBook("Андрей Иванов", "79567567755");
        PhoneBook phoneBook3 = new PhoneBook("Ирина Петрова", "79567567756");

        PhoneBookService phoneBookService = new PhoneBookServiceImpl();

        phoneBookService.addPhoneBook(phoneBook);
        phoneBookService.addPhoneBook(phoneBook2);
        phoneBookService.addPhoneBook(phoneBook3);

        try {
            phoneBookService.addPhoneBook(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(
                phoneBookService.containsPhoneNumber("79567567753") ? "Номер найден" : "Номер отсутствует в книге");

        System.out.println(phoneBookService.find("Ирина Петрова"));
    }
}
