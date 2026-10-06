package ru.otus.java.basic.http.server.processors;

import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.errors_handling.BusinessLogicException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AddRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!request.containsParam("a")) {
            throw new BusinessLogicException("INVALID_INPUT_DATA", "Отсутствует обязательный параметр 'a'");
        }
        if (!request.containsParam("b")) {
            throw new BusinessLogicException("INVALID_INPUT_DATA", "Отсутствует обязательный параметр 'b'");
        }
        int a = Integer.parseInt(request.getParam("a"));
        int b = Integer.parseInt(request.getParam("b"));
        String result = a + " + " + b + " = " + (a + b);
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>" + result + "</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
