package ru.otus.java.basic.http.server;

import ru.otus.java.basic.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final Map<String, RequestProcessor> processors;

    private final RequestProcessor defaultNotFoundRequestProcessor;

    public Dispatcher() {
        this.processors = new HashMap<>();
        this.processors.put("/add", new AddRequestProcessor());
        this.processors.put("/hello", new HelloRequestProcessor());
        this.processors.put("/items", new GetItemsProcessor());
        this.defaultNotFoundRequestProcessor = new DefaultNotFoundRequestProcessor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!processors.containsKey(request.getUri())) {
            defaultNotFoundRequestProcessor.execute(request, output);
            return;
        }
        processors.get(request.getUri()).execute(request, output);
    }
}
