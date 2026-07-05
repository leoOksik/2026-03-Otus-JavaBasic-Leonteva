package ru.otus.java.basic.homework7;

public class TerrainVehicle implements Transport {

    private double petrol;

    private static final double CONSUMPTION = 0.3;

    public TerrainVehicle(double petrol) {
        this.petrol = petrol;
    }

    @Override
    public boolean canMove(TerrainType type, double distance) {
        double countPetrol = distance * CONSUMPTION;

        if (countPetrol > petrol) {
            System.out.printf("Недостаточно бензина для перемещения на дистанцию %.2f км%n", distance);
            return false;
        }
        petrol -= countPetrol;
        return true;
    }

    public String toString() {
        return "terrain vehicle";
    }
}
