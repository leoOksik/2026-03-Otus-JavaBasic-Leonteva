package ru.otus.java.basic.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.dto.ItemDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Long id = Long.parseLong(request.getParam("id"));
        ItemDto itemDto = new ItemDto(id, "Item #" + id, 100 + (id.intValue() * 100) % 500);
        Gson gson = new Gson();
        String strItem = gson.toJson(itemDto);
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                strItem;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
