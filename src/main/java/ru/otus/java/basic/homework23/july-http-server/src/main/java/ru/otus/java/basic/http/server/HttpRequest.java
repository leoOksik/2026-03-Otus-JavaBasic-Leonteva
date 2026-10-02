package ru.otus.java.basic.http.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String rawRequest;

    private String httpMethod;

    private String uri;

    private final Map<String, String> params;

    public String getUri() {
        return uri;
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public HttpRequest(String rawRequest) {
        this.params = new HashMap<>();
        this.rawRequest = rawRequest;
        this.parse();
    }

    public void info(boolean showRawRequest) {
        if (showRawRequest) {
            System.out.println(rawRequest);
        }
        System.out.println("HTTP METHOD: " + httpMethod);
        System.out.println("URI: " + uri);
        System.out.println("PARAMS: " + params);
    }

    private void parse() {
        int start = rawRequest.indexOf(' ');
        int end = rawRequest.indexOf(' ', start + 1);
        httpMethod = rawRequest.substring(0, start);
        uri = rawRequest.substring(start + 1, end);
        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];
            String[] keysValues = tokens[1].split("&");
            for (String o : keysValues) {
                String[] keyValue = o.split("=", 2);
                params.put(keyValue[0], keyValue[1]);
            }
        }
    }
}
