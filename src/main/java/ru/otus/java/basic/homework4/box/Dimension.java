package ru.otus.java.basic.homework4.box;

public class Dimension {

    private final double length;
    private final double width;
    private final double height;

    public Dimension(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Размеры должны быть > 0");
        }

        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Size: %.2f x %.2f x %.2f".formatted(length, width, height);
    }
}
