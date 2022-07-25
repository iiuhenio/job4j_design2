package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = List.of();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines().filter(x -> "404".equals(x.split(" ")[8]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Чтобы убрать задержку в выполнении программы, нужно использовать буферизированные потоки.
     * Исходный поток ввода - это файл FileOutputStream. Он записывает данные по байтам.
     * Блокирует всю программу, пока запись не закончится. Это плохо.
     *
     * Первая обертка - это BufferedOutputStream. Это буфер, который собираем переданные в него
     * байты. Аккумулирует их и постепенно отдает их в FileOutputStream.
     * В этом случае программа не блокируется до тех пока в буфере есть место.
     *
     * Вторая обертка над буфером - это PrintWriter. Мы знаем, что будем записывать текст.
     * В Java есть удобное АПИ для этого, например, PrintWriter поддерживает метод println()
     * для записи данных с последующим переходом на новую строку.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String a : log) {
                out.println(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String a : log) {
            System.out.println(a);
            save(log, "404.txt");
        }
    }
}