package ru.otus.server;

public class ServerApp {
    public static void main(String[] args) {
        new ServerServiceImpl(8189).start();
    }
}
