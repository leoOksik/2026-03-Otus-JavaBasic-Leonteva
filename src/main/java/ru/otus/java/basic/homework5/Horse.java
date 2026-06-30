package ru.otus.java.basic.homework5;

public class Horse extends Animal {

    public Horse(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    public double swim(int distance) {
        if (distance * 4 > getEndurance()) {
            setTired(true);
            System.out.println("Недостаточно выносливости для заплыва");
            return -1;
        }
        setEndurance(getEndurance() - distance * 4);
        double time = distance / getSwimSpeed();
        System.out.printf("%s проплыл за %.2f сек.%n", getName(), time);
        return time;
    }
}
