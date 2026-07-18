package ru.otus.java.basic.homework7;

public class Horse extends ResourceVehicle {

    public Horse(double resource) {
        super(resource, 0.06);
    }

    @Override
    protected boolean isTerrainTypeAllowed(TerrainType type) {
        return type != TerrainType.SWAMP;
    }

    @Override
    public String toString() {
        return "horse";
    }

}
