package ru.otus.java.basic.homework5;

public abstract class Animal {

    private final String name;
    private final double runSpeed;
    private final double swimSpeed;
    private int endurance;
    private boolean isTired;

    public Animal(String name, double runSpeed, double swimSpeed, int endurance) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.endurance = endurance;
        this.isTired = false;
    }

    public double run(int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Дистанция должна быть >= 0");
        }

        if (distance > endurance) {
            isTired = true;
            System.out.println("Выносливости не хватает на забег");
            return -1;
        }
        endurance -= distance;
        double time = distance / runSpeed;
        System.out.printf("%s пробежал за %.2f секунд%n", name, time);
        return time;
    }

    protected int getEnduranceCoeff() {
        return 0;
    }

    public double swim(int distance) {

        int enduranceCoeff = getEnduranceCoeff();

        if (distance * enduranceCoeff > endurance) {
            isTired = true;
            System.out.println("Недостаточно выносливости для заплыва");
            return -1;
        }
        endurance = endurance - distance * enduranceCoeff;
        double time = distance / getSwimSpeed();
        System.out.printf("%s проплыл за %.2f сек.%n", getName(), time);
        return time;
    }

    public void info() {
        System.out.printf("%s%nВыносливость: %d%nУсталость: %s%n",
                name, endurance, isTired);
    }

    public double getSwimSpeed() {
        return swimSpeed;
    }

    public String getName() {
        return name;
    }

    public int getEndurance() {
        return endurance;
    }
}
