package ru.otus.java.basic.homework7;

public class Horse implements Transport {
    private double power;

    private static final double CONSUMPTION = 0.06;

    public Horse(double power) {
        this.power = power;
    }

    @Override
    public boolean canMove(TerrainType type, double distance) {
        if (type == TerrainType.SWAMP) {
            System.out.printf("Местность %s не предназначена для перемещения на %s%n", type.getValue(), this);
            return false;
        }
        double countPower = distance * CONSUMPTION;
        if (countPower  > power) {
            System.out.printf("Недостаточно сил для перемещения на дистанцию %.2f км%n", distance);
            return false;
        }
        power -= countPower ;
        return true;
    }

    public String toString() {
        return "horse";
    }
}

