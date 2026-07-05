package ru.otus.java.basic.homework7;

public class Car implements Transport {

    private double petrol;

    private static final double CONSUMPTION = 0.1;

    public Car(double petrol) {
        this.petrol = petrol;
    }

    @Override
    public boolean canMove(TerrainType type, double distance) {
        if (type == TerrainType.DENSE_FOREST || type == TerrainType.SWAMP) {
            System.out.printf("Местность %s не предназначена для перемещения на %s%n", type.getValue(), this);
            return false;
        }
        double countPetrol = distance * CONSUMPTION;
        if (countPetrol > petrol) {
            System.out.printf("Недостаточно бензина для перемещения на дистанцию %.2f км %n", distance);
            return false;
        }
        petrol -= countPetrol;
        return true;
    }

    public String toString() {
        return "car";
    }
}
