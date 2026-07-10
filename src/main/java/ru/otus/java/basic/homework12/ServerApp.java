package ru.otus.java.basic.homework12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerApp {

    private static final String REGEX = "\\s*(-?\\d+)\\s*(-?\\d+)\\s*([+\\-*/])\\s*";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server started in port: " + serverSocket.getLocalPort());

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    SocketAddress clientAddress = socket.getRemoteSocketAddress();
                    System.out.println("Клиент подключился: " + clientAddress);
                    String operator = "+,-,*,/";
                    String pattern = "\nШаблон передачи: число число оператор";
                    socket.getOutputStream().write((operator + pattern).getBytes());

                    while (true) {
                        byte[] buf = new byte[8192];
                        int bytes = socket.getInputStream().read(buf);
                        if (bytes == -1) {
                            System.out.println("Клиент отключился: " + clientAddress);
                            break;
                        }
                        String message = new String(buf, 0, bytes, StandardCharsets.UTF_8);

                        try {
                            int result = calculateExpression(message);
                            socket.getOutputStream().write(("\nРезультат операции = " + result).getBytes());

                        } catch (IllegalArgumentException | ArithmeticException ex) {
                            socket.getOutputStream().write(ex.getMessage().getBytes());
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int calculateExpression(String message) {
        Matcher matcher = PATTERN.matcher(message);

        if (matcher.matches()) {
            String num1 = matcher.group(1);
            String num2 = matcher.group(2);
            String operator = matcher.group(3);

            int a = Integer.parseInt(num1);
            int b = Integer.parseInt(num2);

            return switch (operator) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> {
                    if (b == 0) throw new ArithmeticException("Деление на ноль запрещено");
                    yield a / b;
                }
                default -> throw new IllegalArgumentException("Неизвестный оператор");
            };
        } else {
            throw new IllegalArgumentException("Некорректные данные");
        }
    }
}
