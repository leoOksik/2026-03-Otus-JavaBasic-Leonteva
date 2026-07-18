package ru.otus.java.basic.homework5;

public class Dog extends Animal {


    public Dog(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    protected int getEnduranceCoeff() {
        return 2;
    }
}
