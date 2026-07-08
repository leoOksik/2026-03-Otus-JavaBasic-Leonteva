package ru.otus.java.basic.homework13;

public class Homework13 {
    public static void main(String[] args) throws InterruptedException {

        fillArrayWithoutThread();
        fillArrayWithThread();
    }

    public static void fillArrayWithoutThread() {
        double[] array = new double[100_000_000];

        long start = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        long end = System.nanoTime();
        double seconds = (double) (end - start) / 1_000_000_000;
        System.out.printf("Время выполнения без использования потоков: %.2f сек", seconds);
    }

    public static void fillArrayWithThread() throws InterruptedException {
        double[] array = new double[100_000_000];

        long start = System.nanoTime();

        int length = array.length;
        int oneLength = length / 4;
        int secondLength = oneLength * 2;
        int thirdLength = oneLength * 3;
        int fourthLength = length;

        Thread oneThread = new Thread(() -> {
            for (int i = 0; i < oneLength; i++) {
                array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread secondThread = new Thread(() -> {
            for (int i = oneLength; i < secondLength; i++) {
                array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread thirdThread = new Thread(() -> {
            for (int i = secondLength; i < thirdLength; i++) {
                array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        Thread fourthThread = new Thread(() -> {
            for (int i = thirdLength; i < fourthLength; i++) {
                array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });

        oneThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();

        oneThread.join();
        secondThread.join();
        thirdThread.join();
        fourthThread.join();

        long end = System.nanoTime();
        double seconds = (double) (end - start) / 1_000_000_000;
        System.out.printf("%nВремя выполнения c использованием потоков: %.2f сек", seconds);
    }
}
