package ru.otus.java.basic.homework4.box;

public class Box {

    private final Dimension dimension;
    private String color;
    private boolean isOpened;
    private boolean isFilled;
    private Item item;

    public Box(Dimension dimension, String color) {
        this.dimension = dimension;
        this.color = color;
        this.isOpened = false;
        this.isFilled = false;
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
        if (isOpened && !isFilled) {
            this.item = item;
            isFilled = true;
            System.out.printf("Предмет %s помещён в коробку%n", item.getName());

        } else if (!isOpened) {
            System.out.println("Коробка закрыта. Откройте, чтобы поместить предмет в коробку");
        } else {
            System.out.printf("Вы не можете поместить %s, так как в коробке уже есть предмет - %s%n",
                    item.getName(), this.item.getName());
        }
    }

    public void popItem() {
        if (isOpened && isFilled) {
            this.item = null;
            isFilled = false;
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
