package ru.otus.java.basic.homework10;

import java.util.List;

public interface FileService {

    List<String> getFiles();

    String readFile(String fileName);

    boolean writeToFile(String fileName, String text);
}
