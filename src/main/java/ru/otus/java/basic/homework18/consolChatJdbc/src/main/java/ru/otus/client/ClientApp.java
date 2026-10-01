package ru.otus.client;

import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        new ClientServiceImpl("localhost", 8189).start(sc);
    }
}
