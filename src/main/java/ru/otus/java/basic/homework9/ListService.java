package ru.otus.java.basic.homework9;

import java.util.ArrayList;
import java.util.List;

public final class ListService {

    private ListService() {
    }

    public static ArrayList<Integer> numbersSequence(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Maximum number must be more than minimum number");
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int sumNumMoreThanFive(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            if (num > 5) {
                sum += num;
            }
        }
        return sum;
    }

    public static List<Integer> rewriteNumbers(int num, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, num);
        }
        return list;
    }

    public static List<Integer> increaseEl(int num, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + num);
        }
        return list;
    }
}
