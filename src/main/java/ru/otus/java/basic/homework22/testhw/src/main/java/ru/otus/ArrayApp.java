package ru.otus;

public class ArrayApp {

    private ArrayApp() {
    }

    public static int[] methodOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                index = i;
            }
        }
        if (index == -1) {
            throw new RuntimeException("Array does not contain the number 1");
        }

        int[] result = new int[arr.length - index - 1];
        for (int j = index + 1, i = 0; j < arr.length; j++, i++) {
            result[i] = arr[j];
        }
        return result;
    }

    public static boolean methodTwo(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        int countOne = 0;
        int countTwo = 0;
        
        for (int value : arr) {
            if (value == 1) {
                countOne++;
            } else if (value == 2) {
                countTwo++;
            } else {
                return false;
            }
        }
        return countOne > 0 && countTwo > 0;
    }
}
