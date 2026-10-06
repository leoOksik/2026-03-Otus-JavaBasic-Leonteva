package ru.otus.java.basic.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.app.ItemsService;
import ru.otus.java.basic.http.server.app.dto.ItemDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public GetItemsProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String result = null;
        Gson gson = new Gson();
        if (!request.containsParam("id")) {
            result = gson.toJson(itemsService.getAll());
        } else {
            Long id = Long.parseLong(request.getParam("id"));
            ItemDto itemDto = itemsService.getById(id);
            result = gson.toJson(itemDto);
        }
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                result;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
