package ru.otus.java.basic.homework5;

public class Horse extends Animal {

    public Horse(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    protected int getEnduranceCoeff() {
        return 4;
    }

}
