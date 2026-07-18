package ru.otus.java.basic.homework7;

public enum TerrainType {
    DENSE_FOREST("густой лес"),
    PLAIN("равнина"),
    SWAMP("болото");

    private final String value;

    TerrainType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
