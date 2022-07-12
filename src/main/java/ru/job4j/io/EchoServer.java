package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ErrorManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Сначала нужно создать сервер, в конструкторе указываем номер порта
 *
 *
 * Вызов метода accept() заставляет программу ждать подключений по указанному порту,
 * работа программы продолжится только после подключения клиента.
 * После успешного подключения метод возвращает объект Socket,
 * который используется для взаимодействия с клиентом.
 *
 * С помощью объекта Socket программа может получить входной поток и может отправить данные в выходной поток.
 *
 * В ответ мы записываем строчку.
 * out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
 *
 * далее в программе читается весь входной поток.
 *
 * после чтения отправляем весь ответ окончательно (out.flush())
 *
 * Следующее условие говорит о том, что сервер работает, пока его принудительно не закроют
 * while (!server.isClosed()) {
 *
 * метод ассеpt принимает один запрос от клиента, чтобы отправить второй,
 * программа должна снова получить объект socket.
 */
public class EchoServer extends UsageLog4j {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            int client = 0;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                System.out.println("Client accepted " + client++);
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("/?msg=Hello")) {
                        out.write("Hello!".getBytes());
                    } else if (str.contains("/?msg=Exit")) {
                        server.close();
                    } else if (str.contains("/?msg=What")) {
                        out.write("What".getBytes());
                    }
                    System.out.println(str);
                    out.flush();
                }
            }
        }
        catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}