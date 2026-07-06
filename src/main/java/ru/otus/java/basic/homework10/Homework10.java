package ru.otus.java.basic.homework10;

import java.util.List;
import java.util.Scanner;

public class Homework10 {
    public static void main(String[] args) {

        FileService fileService = new FileServiceImpl();
        List<String> files = fileService.getFiles();
        System.out.println(files);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name file");
        String fileName = scanner.nextLine();
        System.out.println(fileService.readFile(fileName));

        System.out.println("Enter text");
        String text = scanner.nextLine();
        System.out.println(fileService.writeToFile(fileName, text));
    }
}
