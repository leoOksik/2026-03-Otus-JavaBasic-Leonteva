package ru.otus.java.basic.homework4.box;


public class Homework4 {
    public static void main(String[] args) {

        Box box = new Box(new Dimension(10.5, 5.45, 7.77), "black");
        box.print();
        box.setColor("blue");
        box.print();

        box.pushItem(new Item("charger"));
        box.print();

        box.openBox();
        box.pushItem(new Item("charger"));
        box.print();

        box.pushItem(new Item("charger2"));
        box.closeBox();
        box.print();

        box.popItem();
        box.print();

        box.openBox();
        box.popItem();
        box.print();
    }
}
