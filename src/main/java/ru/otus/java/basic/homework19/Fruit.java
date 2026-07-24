package ru.otus.java.basic.homework19;

public class Fruit implements Comparable<Fruit>{

    private final String typeFruit;
    private final double weight;

    public Fruit(String name, double weight) {
        this.typeFruit = name;
        this.weight = weight;
    }

    public String getName() {
        return typeFruit;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return """
            Название: %s,
            Вес: %.2f гр.
            """.formatted(this.typeFruit, this.weight);
    }

    @Override
    public int compareTo(Fruit o) {
        return this.typeFruit.compareTo(o.typeFruit);
    }
}
