package ru.otus.java.basic.homework5;

public class Dog extends Animal {


    public Dog(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    public double swim(int distance) {
        if (distance * 2 > getEndurance()) {
            setTired(true);
            System.out.println("Недостаточно выносливости для заплыва");
            return -1;
        }
        setEndurance(getEndurance() - distance * 2);
        double time = distance / getSwimSpeed();
        System.out.printf("%s проплыл за %.2f сек.%n", getName(), time);
        return time;
    }
}
