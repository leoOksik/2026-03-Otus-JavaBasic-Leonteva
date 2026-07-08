package ru.otus.java.basic.homework12;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            byte[] buf = new byte[8192];
            int bytes = socket.getInputStream().read(buf);
            String message = new String(buf, 0, bytes, StandardCharsets.UTF_8);
            System.out.println(message);

            Scanner input = new Scanner(System.in);

            while (true) {
                String str = input.nextLine();
                if (str.isEmpty()) {
                    continue;
                }
                if (str.equals("ex")) {
                    break;
                }
                socket.getOutputStream().write(str.getBytes());

                bytes = socket.getInputStream().read(buf);
                String result = new String(buf, 0, bytes, StandardCharsets.UTF_8);
                System.out.println(result);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
