package ru.otus.java.basic.homework5;

public class Cat extends Animal {

    public Cat(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    public double swim(int distance) {
        System.out.println("Кот плавать не умеет");
        return -1;
    }
}
