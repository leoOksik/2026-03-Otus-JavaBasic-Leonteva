package ru.otus.java.basic.homework20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }

            try {
                String[] words = checkInputAndSplit(input);
                System.out.println(findSequenceCharacter(words));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("File not found " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error reading file " + e.getMessage());
            }
        }
    }

    public static int findSequenceCharacter(String[] words) throws IOException {
        String fileName = words[0].endsWith(".txt") ? words[0] : words[0] + ".txt";
        String sequence = words[1];

        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                result += countSequence(sequence, str);
            }
        }
        return result;
    }

    private static int countSequence(String subStr, String str) {
        if (str.length() < subStr.length()) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i <= str.length() - subStr.length(); i++) {
            if (str.regionMatches(false, i, subStr, 0, subStr.length())) {
                result++;
            }
        }
        return result;
    }

    private static String[] checkInputAndSplit(String input) {
        Objects.requireNonNull(input, "Input string should not be null");
        if (input.isBlank()) {
            throw new IllegalArgumentException("Input string should not be blank");
        }
        String[] words = input.trim().split("\\s+");

        if (words.length != 2) {
            throw new IllegalArgumentException("Input string should have 2 words: file name and sequence");
        }
        return words;
    }
}
