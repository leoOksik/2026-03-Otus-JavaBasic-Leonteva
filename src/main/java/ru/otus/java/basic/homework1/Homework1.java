package ru.otus.java.basic.homework1;

import java.util.Random;
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int num = 0;

        while (num != 6) {
            printMenu();
            if (sc.hasNextInt()) {
                num = sc.nextInt();
            } else {
                sc.nextLine();
                continue;
            }

            switch (num) {
                case 1 -> greetings();
                case 2 -> {
                    int a = (int) (Math.random() * 21) - 10;
                    int b = (int) (Math.random() * 21) - 10;
                    int c = (int) (Math.random() * 21) - 10;
                    checkSign(a, b, c);
                }
                case 3 -> selectColor();
                case 4 -> compareNumbers();
                case 5 -> {
                    int initValue = rand.nextInt(100);
                    int delta = rand.nextInt(100);
                    boolean increment = rand.nextBoolean();
                    addOrSubtractAndPrint(initValue, delta, increment);
                }
                case 6 -> System.out.println("Выход из системы");
                default -> System.out.println("Неверный номер меню");
            }
        }
    }

    public static void greetings() {
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor() {
        int data = (int) (Math.random() * 100);

        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = (int) (Math.random() * 100);
        int b = (int) (Math.random() * 100);

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            initValue += delta;
        } else {
            initValue -= delta;
        }
        System.out.println("initValue = " + initValue);
    }

    public static void printMenu() {
        System.out.println("\nведите номер меню от 1 до 6");
        System.out.println("1. Метод greetings()");
        System.out.println("2. Метод checkSign(a, b, c)");
        System.out.println("3. Метод selectColor()");
        System.out.println("4. Метод compareNumbers()");
        System.out.println("5. Метод addOrSubtractAndPrint(initValue, delta, increment)");
        System.out.println("6. Выход\n");
    }
}
