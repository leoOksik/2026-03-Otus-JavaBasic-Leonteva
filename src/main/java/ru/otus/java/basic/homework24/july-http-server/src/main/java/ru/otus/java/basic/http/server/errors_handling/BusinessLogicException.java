package ru.otus.java.basic.http.server.errors_handling;

public class BusinessLogicException extends RuntimeException {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BusinessLogicException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
