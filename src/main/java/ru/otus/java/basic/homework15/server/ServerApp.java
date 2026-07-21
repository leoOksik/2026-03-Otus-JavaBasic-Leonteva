package ru.otus.java.basic.homework15.server;

public class ServerApp {
    public static void main(String[] args) {
        new ServerServiceImpl(8189).start();
    }
}
