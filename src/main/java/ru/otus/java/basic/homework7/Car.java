package ru.otus.java.basic.homework7;

public class Car extends ResourceVehicle {

    public Car(double resource) {
        super(resource, 0.1);
    }

    @Override
    protected boolean isTerrainTypeAllowed(TerrainType type) {
        return type != TerrainType.DENSE_FOREST && type != TerrainType.SWAMP;
    }

    @Override
    public String toString() {
        return "car";
    }
}
