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

    abstract public double swim(int distance);

    public void info() {
        System.out.printf("%s%nВыносливость: %d%nУсталость: %s%n",
                name, endurance, isTired);
    }

    public double getSwimSpeed() {
        return swimSpeed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setTired(boolean tired) {
        isTired = tired;
    }

    public String getName() {
        return name;
    }
}
