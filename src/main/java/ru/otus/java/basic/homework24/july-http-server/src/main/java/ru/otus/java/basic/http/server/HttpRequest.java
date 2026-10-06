package ru.otus.java.basic.http.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class HttpRequest {

    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

    private String rawRequest;
    private HttpMethod httpMethod;
    private String uri;
    private String body;
    private Map<String, String> params;
    private Map<String, String> headers;

    public HttpRequest(String rawRequest) {
        this.params = new HashMap<>();
        this.headers = new TreeMap<>();
        this.rawRequest = rawRequest;
        this.parse();
        this.parseHeaders();
    }

    public String getBody() {
        return body;
    }

    public String getUri() {
        return uri;
    }

    public String getRoutingKey() {
        return httpMethod + " " + uri;
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public boolean containsParam(String key) {
        return params.containsKey(key);
    }

    public void info(boolean showRawRequest) {
        if (showRawRequest) {
            log.info(rawRequest);
        }
        log.info("HTTP METHOD: {}",httpMethod);
        log.info("URI: {}",uri);
        log.info("PARAMS: {}",params);
        headers.forEach((key, value) -> log.info("{}: {}", key, value));
    }

    private void parse() {
        int start = rawRequest.indexOf(' ');
        int end = rawRequest.indexOf(' ', start + 1);
        httpMethod = HttpMethod.valueOf(rawRequest.substring(0, start));
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
        if (httpMethod == HttpMethod.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }

    private void parseHeaders () {
        String [] tokens = rawRequest.split("\r\n");
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].isEmpty()) {
                break;
            }
            String[] data = tokens[i].split(":", 2);
            headers.put(data[0].trim(), data[1].trim());
        }
    }
}
