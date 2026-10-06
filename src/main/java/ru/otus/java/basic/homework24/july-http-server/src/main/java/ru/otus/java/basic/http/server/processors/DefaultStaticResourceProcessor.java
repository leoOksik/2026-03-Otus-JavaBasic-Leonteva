package ru.otus.java.basic.http.server.processors;

import ru.otus.java.basic.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultStaticResourceProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        // 1.txt
        String filename = request.getUri().substring(1);
        Path filePath = Paths.get("static/", filename);
        System.out.println("Рабочая директория: " + Path.of("").toAbsolutePath());
        byte[] fileData = Files.readAllBytes(filePath);

        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: " + fileData.length + "\r\n" +
                "\r\n";
        output.write(response.getBytes(StandardCharsets.UTF_8));
        output.write(fileData);
    }
}
