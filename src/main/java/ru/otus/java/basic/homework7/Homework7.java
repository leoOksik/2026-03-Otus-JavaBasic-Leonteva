package ru.otus.java.basic.homework7;

public class Homework7 {
    public static void main(String[] args) {

        Human human = new Human("Alex");

        Transport car = new Car(50);
        Transport terrainVehicle = new TerrainVehicle(70);
        Transport horse = new Horse(10);
        Transport bicycle = new Bicycle();

        human.getOn(car);
        human.getOn(terrainVehicle);

        human.move(TerrainType.DENSE_FOREST, 149.45);
        human.move(TerrainType.PLAIN, 10);

        human.getOff();
        human.getOff();
        human.move(TerrainType.DENSE_FOREST,10);

        human.getOn(horse);

        human.move(TerrainType.DENSE_FOREST, 200);
        human.move(TerrainType.DENSE_FOREST, 8.5);

        human.getOff();

        human.getOn(bicycle);
    }
}
