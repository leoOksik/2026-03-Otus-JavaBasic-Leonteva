package ru.otus.java.basic.homework2;

import java.util.Arrays;

public class Homework2Simple {
    public static void main(String[] args) {

        int[] arr = {6, 1, 0, -3, 8, 9};
        String str = "Some string\n";
        int[] arr2 = new int[5];

        System.out.println("Task 1");
        printStrNCount(5, str);

        System.out.println("Task 2");
        printSumOfElemGraterThanFive(arr);

        System.out.println("Task 3");
        fillArray(6, arr2);
        Arrays.stream(arr2).forEach(System.out::println);

        System.out.println("Task 4");
        increaseArrayNumbers(3, arr);
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println("Task 5");
        printWhichSideOfArrMore(arr);
    }

    public static void printStrNCount(int count, String str) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static void printSumOfElemGraterThanFive(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (num > 5) {
                sum += num;
            }
        }
        System.out.println(sum);
    }

    public static void fillArray(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = num;
        }
    }

    public static void increaseArrayNumbers(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += num;
        }
    }

    public static void printWhichSideOfArrMore(int[] arr) {
        int sumLeft = 0;
        int sumRight = 0;
        int side = arr.length / 2;
        for (int i = 0; i < arr.length; i++) {
            if (i  < side) {
                sumLeft += arr[i];
                continue;
            }
                sumRight += arr[i];
        }
        if (sumLeft > sumRight) {
            System.out.printf("sumLeft (%d) > sumRight (%d)", sumLeft, sumRight);
        }
        else if (sumLeft < sumRight){
            System.out.printf("sumLeft (%d) < sumRight (%d)",  sumLeft, sumRight);
        }
        else {
            System.out.printf("sumLeft (%d) = sumRight (%d)",  sumLeft, sumRight);
        }
    }
}
