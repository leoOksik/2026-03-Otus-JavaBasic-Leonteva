package ru.otus.java.basic.homework6;

public class Homework6 {
    public static void main(String[] args) {

        Cat[] cats = new Cat[]{
                new Cat("Barsik", 90),
                new Cat("Murzik", 70),
                new Cat("Marks", 150)
        };

        Plate plate = new Plate(200);

        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.print(cat);
        }

        System.out.printf("Текущее количество еды в тарелке после кормления: %d%n", plate.getCurrentAmount());

        plate.addAmount(700);

        System.out.printf("Текущее количество еды в тарелке после добавления: %d%n", plate.getCurrentAmount());

    }
}
