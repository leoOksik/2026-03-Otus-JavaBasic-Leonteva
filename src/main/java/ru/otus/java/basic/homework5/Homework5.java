package ru.otus.java.basic.homework5;


public class Homework5 {
    public static void main(String[] args) {

        Animal cat = new Cat("Bars", 3, 30);
        Animal dog = new Dog("Mars", 5, 3, 50);
        Animal horse = new Horse("Milli", 10, 5, 100);

        cat.run(10);
        cat.info();

        cat.swim(5);
        cat.info();

        dog.run(3);
        dog.info();

        dog.swim(2);
        dog.info();

        horse.run(6);
        horse.info();

        horse.swim(105);
        horse.info();
    }
}
