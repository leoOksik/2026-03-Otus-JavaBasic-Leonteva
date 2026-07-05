package ru.otus.java.basic.homework7;

public class Bicycle implements Transport {

    @Override
    public boolean canMove(TerrainType type, double distance) {
        if (type == TerrainType.SWAMP) {
            System.out.printf("Местность %s не предназначена для перемещения на %s%n", type.getValue(), this);
            return false;
        }
        return true;
    }

    public String toString() {
        return "bicycle";
    }
}
