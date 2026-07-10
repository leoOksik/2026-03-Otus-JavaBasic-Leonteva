package ru.otus.java.basic.homework13;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        int countThread = 4;
        Thread[] threads = new Thread[countThread];
        int length = array.length;
        int step = length / 4;

        long startTime = System.nanoTime();

        for (int i = 0; i < countThread; i++) {
            int start = i * step;
            int end = (i == countThread - 1) ? length : (i + 1) * step;

            threads[i] = new Thread(() ->
                    IntStream.range(start, end).forEach(j ->
                            array[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2))
            );
        }

        Arrays.stream(threads).forEach(Thread::start);

        for (Thread t : threads) {
            t.join();
        }

        long endTime = System.nanoTime();
        double seconds = (double) (endTime - startTime) / 1_000_000_000;
        System.out.printf("%nВремя выполнения c использованием потоков: %.2f сек", seconds);
    }
}
