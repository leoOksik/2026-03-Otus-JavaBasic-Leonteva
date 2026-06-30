package ru.otus.java.basic.homework3;

import java.util.Arrays;

public class Homework3 {
    public static void main(String[] args) {
        System.out.println("Task 1");
        int[][] arr = {
                {4, -2, 1, 13, -5, 7},
                {1, -15, 3},
                {3, 10, -3}
        };
        System.out.println(sumOfPositiveElements(arr));

        System.out.println("Task 2");
        printSquareOfStars(5);

        System.out.println("Task 3");
        int[][] array = {
                {3, -2, 7, 8},
                {2, 4, 9, 1},
                {1, 5, 2, 3},
                {3, 6, 8, 2}
        };

        zeroDiagonals(array);
        Arrays.stream(array).map(Arrays::toString).forEach(System.out::println);

        System.out.println("Task 4");
        System.out.println(findMax(array));

        System.out.println("Task 5");
        System.out.println(secondStrSum(arr));

    }

    public static int sumOfPositiveElements(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }

    public static void printSquareOfStars(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void zeroDiagonals(int[][] arr) {
        int arrLength = arr.length;

        for (int[] array : arr) {
            if (array.length != arrLength) return;
        }

        for (int i = 0; i < arrLength; i++) {
            arr[i][i] = 0;
            arr[i][arrLength - 1 - i] = 0;
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];

        for (int[] arr : array) {
            for (int elem : arr) {
                if (elem > max) {
                    max = elem;
                }
            }
        }
        return max;
    }

    public static int secondStrSum(int[][] arr) {
        if (arr.length < 2) {
            return -1;
        }
        int sum = 0;
        for (int element: arr[1]) {
            sum += element;
        }
        return sum;
    }
}
