package ru.otus.java.basic.homework7;

public abstract class ResourceVehicle implements Transport {

    protected double resource;
    protected final double consumption;
    private static final double EPSILON = 1e-6;

    public ResourceVehicle(double resource, double consumption) {
        this.resource = resource;
        this.consumption = consumption;
    }

    protected abstract boolean isTerrainTypeAllowed(TerrainType type);

    @Override
    public boolean canMove(TerrainType type, double distance) {
        if (!isTerrainTypeAllowed(type)) {
            System.out.printf("Местность %s не предназначена для перемещения на %s%n",
                    type.getValue(), this);
            return false;
        }
        double neededResource = distance * consumption;
        if (neededResource > resource + EPSILON) {
            System.out.printf("Недостаточно ресурса для перемещения на дистанцию %.2f км %n", distance);
            return false;
        }
        resource -= neededResource;
        return true;
    }

    @Override
    public abstract String toString();
}
