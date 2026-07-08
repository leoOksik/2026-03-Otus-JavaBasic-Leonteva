package ru.otus.java.basic.homework11;

import java.util.*;

public class PhoneBookServiceImpl implements PhoneBookService {

    private final Map<String, Set<String>> phoneBookMap;

    public PhoneBookServiceImpl() {
        this.phoneBookMap = new TreeMap<>();
    }

    @Override
    public void addPhoneBook(PhoneBook phoneBook) {
        if (phoneBook == null || phoneBook.phoneNumber() == null
                || phoneBook.phoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Номер телефона должен быть заполнен");
        }
        if (phoneBook.fullName() == null || phoneBook.fullName().isEmpty()) {
            throw new IllegalArgumentException("ФИО должно быть заполнено");
        }
        phoneBookMap.computeIfAbsent(
                phoneBook.fullName(), k -> new HashSet<>()).add(phoneBook.phoneNumber());

    }

    @Override
    public Set<String> find(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Фио должно быть заполнено (не null и не пустое)");
        }
        Set<String> phones = phoneBookMap.get(fullName);
        return phones == null ? new HashSet<>() : phones;
    }

    @Override
    public boolean containsPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Номер телефона должен быть заполнен (не null и не пустой)");
        }
        for (Set<String> phones : phoneBookMap.values()) {
            if (phones.contains(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
