package ru.otus.java.basic.homework7;

public class TerrainVehicle extends ResourceVehicle {

    public TerrainVehicle(double resource) {
        super(resource, 0.3);
    }

    @Override
    protected boolean isTerrainTypeAllowed(TerrainType type) {
        return true;
    }

    public String toString() {
        return "terrain vehicle";
    }
}
