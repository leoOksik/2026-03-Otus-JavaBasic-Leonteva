package ru.otus.java.basic.http.server;

public class Application {
    // Домашнее задание:
    // - Сделать логирование (не через sout/jul)
    // - Сделать парсинг заголовков
    // - * Добавить возможность удаления продуктов по кнопке на фронте
    
    public static void main(String[] args) {
        new HttpServer(8189).start();
    }
}
