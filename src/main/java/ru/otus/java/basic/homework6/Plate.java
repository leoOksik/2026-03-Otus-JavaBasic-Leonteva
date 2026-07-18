package ru.otus.java.basic.homework6;

public class Plate {

    private final int maxAmount;

    private int currentAmount;

    public Plate(int maxAmount) {
        this.maxAmount = maxAmount;
        this.currentAmount = maxAmount;
    }

    public void addAmount(int amount) {
        if (amount > 0) {
            currentAmount = Math.min(currentAmount + amount, maxAmount);
        }
    }

    public boolean decreaseAmount(int amount) {
        if (currentAmount - amount < 0) {
            return false;
        }
        currentAmount -= amount;
        return true;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    @Override
    public String toString() {
        return """
                max amount: %d%n
                current amount: %d%n
                """
                .formatted(maxAmount, currentAmount);
    }
}
