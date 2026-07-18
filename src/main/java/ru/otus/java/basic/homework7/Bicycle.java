package ru.otus.java.basic.homework7;

public class Bicycle extends ResourceVehicle {

    public Bicycle() {
        super(0.0, 0.0);
    }

    @Override
    protected boolean isTerrainTypeAllowed(TerrainType type) {
        return type != TerrainType.SWAMP;
    }

    public String toString() {
        return "bicycle";
    }
}
