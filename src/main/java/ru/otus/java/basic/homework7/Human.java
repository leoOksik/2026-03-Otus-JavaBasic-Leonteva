package ru.otus.java.basic.homework7;

public class Human {

    private final String name;
    private Transport currentTransport;

    public Human(String name) {
        this.name = name;
    }

    public void getOn(Transport transport) {
        if (this.currentTransport == null) {
            this.currentTransport = transport;
            System.out.printf("%s сел на транспорт %s%n", name, currentTransport);
        } else {
            System.out.printf("%s не вставал с транспорта %s, чтобы пересесть на транспорт %s%n",
                    name, this.currentTransport, transport);
        }
    }

    public void getOff() {
        if (this.currentTransport != null) {
            System.out.printf("%s встал с транспорта %s%n", name, currentTransport);
            this.currentTransport = null;
        } else {
            System.out.printf("%s не садился на транспорт%n", name);
        }
    }

    public boolean move(TerrainType terrainType, double distance) {
        if (currentTransport == null) {
            System.out.printf("%s идет пешком дистанцию %.2f км по местности %s%n",
                    name, distance, terrainType.getValue());
            return true;
        }

        if (currentTransport.canMove(terrainType, distance)) {
            System.out.printf("%s переместился на дистанцию %.2f км на транспорте %s по местности %s%n",
                    name, distance, currentTransport.toString(), terrainType.getValue());
            return true;
        } else {
            System.out.printf("%s не смог переместиться на дистанцию %.2f на транспорте %s по местности %s%n",
                    name, distance, currentTransport.toString(), terrainType.getValue());
            return false;
        }
    }
}
