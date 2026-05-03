package ru.otus.java.basic.homework2;

import java.util.Arrays;
import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) {

        System.out.println("Task 1");
        int[] arrA = {1, 2, 3};
        int[] arrB = {2, 2};
        int[] arrC = {1, 1, 1, 1, 1};
        Arrays.stream(arraysSum(arrA, arrB, arrC)).forEach(System.out::println);

        System.out.println("Task 2");
        int[][] arrD = {
                {1, 1, 1, 1, 1, 5},
                {5, 3, 4, -2},
                {7, 2, 2, 2},
                {9, 4}
        };
        for (int i = 0; i < 4; i++) {
            System.out.println(hasPointBetweenElements(arrD[i]));
        }

        System.out.println("Task 3");
        int[][] arrE = {
                {-3, 0, 1, 5, 7},
                {0, -4, 2, 10, -3},
                {3, 3, 3},
                {6, 1, 0, -2, 19},
                {10, 4, 0, -5}
        };
        String choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("""
                    Enter a sorting order: ASC or DESC
                    If you want to stop you should enter EXIT
                    """);
            choice = sc.nextLine();
            int numArr = 1;
            if (choice.equalsIgnoreCase("ASC") || choice.equalsIgnoreCase("DESC")) {
                for (int[] arr : arrE) {
                    System.out.printf("arr %d is %s\n", numArr++, checkSortingArrByDescOrAsc(arr, choice));
                }
            }
        } while (!choice.equalsIgnoreCase(("EXIT")));

        System.out.println("Task 4");
        int[] arrF = new int[5];
        Arrays.setAll(arrF, i -> (int) (Math.random() * 201) - 100);
        System.out.println("Before reverse");
        Arrays.stream(arrF).forEach(System.out::println);
        reverseArr(arrF);
        System.out.println("After reverse");
        Arrays.stream(arrF).forEach(System.out::println);

    }

    public static int[] arraysSum(int[]... arr) {
        int size = 0;

        for (int[] array : arr) {
            size = Math.max(size, array.length);
        }
        int[] newArr = new int[size];
        for (int[] array : arr) {
            for (int i = 0; i < array.length; i++) {
                newArr[i] += array[i];
            }
        }
        return newArr;
    }

    public static boolean hasPointBetweenElements(int[] arr) {
        int sumLeft = 0;
        int sumRight;
        int sum = Arrays.stream(arr).sum();

        for (int elem : arr) {
            sumLeft += elem;
            sumRight = sum - sumLeft;

            if (sumLeft == sumRight) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSortingArrByDescOrAsc(int[] arr, String choice) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (choice.equalsIgnoreCase("DESC")) {
                if (arr[i] < arr[i + 1]) {
                    return false;
                }
            } else {
                if (arr[i] > arr[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void reverseArr(int[] arr) {
        if (arr.length <= 1) return;
        int size = arr.length / 2;
        for (int i = 0; i < size; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
