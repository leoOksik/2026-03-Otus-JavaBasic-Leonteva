package ru.otus.java.basic.homework11;

import java.util.Set;

public interface PhoneBookService {

    /**
     * Добавляет телефонный номер в телефонную книгу
     * @param phoneBook объект c полями фио абонента и номер телефона
     * @throws IllegalArgumentException если передан null или пустые поля
     */
    void addPhoneBook(PhoneBook phoneBook);

    /**
     * Находит множество телефонов абонента
     * @param fullName фио абонента
     * @return Set<String> множество номеров телефонов
     * @throws IllegalArgumentException если передан null или фио абонента пустое
     */
    Set<String> find(String fullName);

    /**
     * Проверяет, есть ли такой номер в телефонной книге
     * @param phoneNumber телефонный номер
     * @return true/false если найден/ не найден
     * @throws IllegalArgumentException если передан null или номер телефона пустой
     */
    boolean containsPhoneNumber(String phoneNumber);
}
