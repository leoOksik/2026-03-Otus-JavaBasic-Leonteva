package ru.otus.java.basic.homework6;

public class Cat {

    private final String name;

    private final int appetite;

    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate plate) {
        satiety = plate.decreaseAmount(appetite);
    }

    @Override
    public String toString() {
        return """
                Cat's name: %s
                appetite: %d
                satiety: %b%n
                """
                .formatted(name, appetite, satiety);
    }
}
