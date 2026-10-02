package ru.otus.java.basic.http.server.processors;

import ru.otus.java.basic.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response = """
                HTTP/1.1 404 Not Found\r
                Content-Type: text/html\r
                \r
                <html><body><h1>Page Not Found</h1></body></html>""";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
