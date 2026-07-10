package ru.otus.java.basic.homework10;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    public List<String> getFiles() {
        String path = System.getProperty("user.dir");
        File fileDir = new File(path);
        File[] files = fileDir.listFiles();
        List<String> list = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    list.add(file.getName());
                }
            }
        }
        return list;
    }

    @Override
    public String readFile(String fileName) {
        if (isInvalid(fileName)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            int n;
            while ((n = in.read()) != -1) {
                sb.append((char) n);
            }
        } catch (IOException e) {
            System.err.println("Read error " + e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public boolean writeToFile(String fileName, String text) {
        if (isInvalid(fileName)) {
            return false;
        }
        if (text == null || text.isBlank()) {
            return false;
        }

        try (FileOutputStream out = new FileOutputStream(fileName, true)) {
            byte[] buf = text.getBytes(StandardCharsets.UTF_8);
            out.write(System.lineSeparator().getBytes());
            out.write(buf);
            return true;
        } catch (IOException e) {
            System.err.println("Write error " + e.getMessage());
            return false;
        }
    }

    private boolean isInvalid(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return true;
        }
        return !Files.exists(Paths.get(fileName));
    }

}



