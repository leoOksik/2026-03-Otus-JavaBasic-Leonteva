package ru.otus.java.basic.homework19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        Objects.requireNonNull(fruit, "fruit must not be null");
        if (fruit.getWeight() <= 0.0) {
            throw new IllegalArgumentException("fruit weight must be positive and greater than 0");
        }
        fruits.add(fruit);
    }

    public double weight() {
        return fruits.stream()
                .mapToDouble(Fruit::getWeight)
                .sum();
    }

    public boolean compare(Box<? extends Fruit> box) {
        double eps = 1e-6;
        return Math.abs(this.weight() - box.weight()) < eps;
    }

    public void transferToAnotherBox(Box<? super T> box) {
        box.fruits.addAll(this.fruits);
        Collections.sort(box.fruits);
        this.fruits.clear();
    }

    @Override
    public String toString() {
        return fruits.toString();
    }
}
