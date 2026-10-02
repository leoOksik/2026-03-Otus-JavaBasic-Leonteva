package ru.otus.java.basic.http.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;

    private final Dispatcher dispatcher;

    private final ExecutorService executorService;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.executorService = Executors.newFixedThreadPool(15);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            System.out.println("Ожидаем подключения");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Получено входящее подключение");

                executorService.submit(() -> {
                    try (socket) {
                        InputStream in = socket.getInputStream();
                        OutputStream out = socket.getOutputStream();

                        byte[] buffer = new byte[8192];
                        int n = in.read(buffer);

                        if (n > 0) {
                            String rawRequest = new String(buffer, 0, n);
                            HttpRequest request = new HttpRequest(rawRequest);
                            request.info(true);

                            dispatcher.execute(request, out);
                        }
                    } catch (IOException e) {
                        System.err.println("Ошибка при обработке запроса: " + e.getMessage());
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
