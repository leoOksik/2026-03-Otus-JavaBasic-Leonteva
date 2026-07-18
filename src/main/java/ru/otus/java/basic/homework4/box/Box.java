package ru.otus.java.basic.homework4.box;

import java.util.Objects;

public class Box {

    private final Dimension dimension;
    private String color;
    private boolean isOpened;
    private Item item;

    public Box(Dimension dimension, String color) {
        this.dimension = Objects.requireNonNull(dimension, "dimension must not be null");
        this.color = color;
        this.isOpened = false;
        this.item = null;
    }

    public void openBox() {
        isOpened = true;
        System.out.println("Коробка открыта");
    }

    public void closeBox() {
        isOpened = false;
        System.out.println("Коробка закрыта");
    }

    public void setColor(String color) {
        this.color = color;
        System.out.printf("Новый цвет коробки %s%n", color);
    }

    public void pushItem(Item item) {
        if (isOpened && this.item == null) {
            this.item = item;
            System.out.printf("Предмет %s помещён в коробку%n", item.getName());

        } else if (!isOpened) {
            System.out.println("Коробка закрыта. Откройте, чтобы поместить предмет в коробку");
        } else {
            System.out.printf("Вы не можете поместить %s, так как в коробке уже есть предмет - %s%n",
                    item.getName(), this.item.getName());
        }
    }

    public void popItem() {
        if (isOpened && this.item != null) {
            this.item = null;
            System.out.println("Предмет убран. Коробка пуста");

        } else if (!isOpened) {
            System.out.println("Коробка закрыта. Откройте, чтобы убрать предмет");
        } else {
            System.out.println("В коробке нет предмета");
        }
    }

    public void print() {
        System.out.printf("""
                %s
                Color: %s
                %s
                %n""",
                dimension, color, item == null ? "Item: -" : item);
    }
}
