package ru.otus.java.basic.homework21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Homework21 {
    private static final int COUNT_REPEATS = 5;
    private static final int COUNT_THREADS = 3;
    private static final int INDEX_LAST_THREAD = COUNT_THREADS - 1;

    private static final Semaphore[] semaphores = {
            new Semaphore(1), new Semaphore(0), new Semaphore(0)
    };

    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(COUNT_THREADS)) {
            executor.execute(() -> printLetter("A", 0));
            executor.execute(() -> printLetter("B", 1));
            executor.execute(() -> printLetter("C", 2));
        }
    }

    private static void printLetter(String letter, int index) {
        for (int i = 0; i < COUNT_REPEATS; i++) {
            try {
                semaphores[index].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(letter);
            int next = index == INDEX_LAST_THREAD ? 0 : index + 1;
            semaphores[next].release();
        }
    }
}
