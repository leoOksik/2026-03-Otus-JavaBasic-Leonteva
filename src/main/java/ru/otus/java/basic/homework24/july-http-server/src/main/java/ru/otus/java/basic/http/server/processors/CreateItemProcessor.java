package ru.otus.java.basic.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.app.ItemsService;
import ru.otus.java.basic.http.server.app.dto.ItemDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateItemProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public CreateItemProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        ItemDto itemDto = itemsService.createNewItem(gson.fromJson(request.getBody(), ItemDto.class));
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                gson.toJson(itemDto);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
