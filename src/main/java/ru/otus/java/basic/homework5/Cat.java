package ru.otus.java.basic.homework5;

public class Cat extends Animal {

    public Cat(String name, double runSpeed, int endurance) {
        super(name, runSpeed, 0.0, endurance);
    }

    @Override
    public double swim(int distance) {
        System.out.println("Кот плавать не умеет");
        return -1;
    }
}
