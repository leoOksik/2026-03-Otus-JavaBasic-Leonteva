package ru.otus.java.basic.homework8;

import java.util.Arrays;

public class Homework8 {
    public static void main(String[] args) {

        String[][] incorrectSizeArray = new String[][]{
                {"534", "qwe"},
                {"fgh", "44", "$$", "4530"}
        };

        String[][] correctSizeArrayWithIncorrectData = new String[][]{
                {"534", "qwe", "45", "11%"},
                {"fgh", "44", "$$", "4530"},
                {"12", "13", "1", "0"},
                {"UOI", "88h5", "99", "2"}
        };

        String[][] correctSizeArrayWithCorrectData = new String[][]{
                {"2", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "2", "-5", "-10"},
                {"-2", "0", "4", "2"}
        };

        try {
            System.out.printf("Сумма элементов = %d", sumElements(incorrectSizeArray));
        } catch (AppArraySizeException ex) {
            System.err.println(ex.getMessage());
        } catch (AppArrayDataException ex) {
            System.err.printf("%s: [%d][%d]: %n",
                    ex.getMessage(), ex.getRow(), ex.getCol());
        }

        try {
            System.out.printf("Сумма элементов = %d", sumElements(correctSizeArrayWithIncorrectData));
        } catch (AppArraySizeException ex) {
            System.err.println(ex.getMessage());
        } catch (AppArrayDataException ex) {
            System.err.printf("%s: [%d][%d]: %n",
                    ex.getMessage(), ex.getRow(), ex.getCol());
        }

        try {
            System.out.printf("Сумма элементов = %d", sumElements(correctSizeArrayWithCorrectData));
        } catch (AppArraySizeException ex) {
            System.err.println(ex.getMessage());
        } catch (AppArrayDataException ex) {
            System.err.printf("%s: [%d][%d]: %n",
                    ex.getMessage(), ex.getRow(), ex.getCol());
        }
    }

    public static int sumElements(String[][] arr) {
        if (arr == null || arr.length != 4 ||
                Arrays.stream(arr).anyMatch(row -> row == null || row.length != 4)) {
            throw new AppArraySizeException("Incorrect size. Array size must be 4x4");
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String s = arr[i][j];

                try {
                    sum += Integer.parseInt(s);
                } catch (NumberFormatException ex) {
                    throw new AppArrayDataException("Incorrect data", i, j);
                }
            }
        }
        return sum;
    }
}
