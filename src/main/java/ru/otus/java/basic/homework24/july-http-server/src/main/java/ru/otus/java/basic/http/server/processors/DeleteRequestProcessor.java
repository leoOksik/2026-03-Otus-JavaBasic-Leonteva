package ru.otus.java.basic.http.server.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.app.ItemsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteRequestProcessor implements RequestProcessor{

    private ItemsService itemsService;

    public DeleteRequestProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        if (request.containsParam("id")) {
            Long id = Long.parseLong(request.getParam("id"));
            itemsService.deleteItem(id);
        }
        String result = gson.toJson(itemsService.getAll());
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                result;

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
