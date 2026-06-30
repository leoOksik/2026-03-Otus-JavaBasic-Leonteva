package ru.otus.java.basic.homework4.box;

public class Item {

    private final String name;

    public Item(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя предмета не может быть пустым или null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item: %s".formatted(name);
    }
}
