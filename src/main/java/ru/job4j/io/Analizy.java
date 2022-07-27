package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание:
 * 1. Реализуйте метод unavailable(). source - путь к файлу лога.
 * target - имя путь к файлу результата анализа.
 * 2. Метод unavailable() должен находить диапазоны, когда сервер не работал.
 * Сервер не работал, если status = 400 или 500.
 * Диапазоном считается последовательность статусов 400 и 500
 */
public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = true; // булин Флаг = тру
        String start = null; // булин Старт = нулл
        String end; // стринг Енд
        List<String> status = new ArrayList<>(); // Коллекция Статус
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) { // читаем Сурс
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) { // читаем
                String[] out = line.split(" "); // в массив типа стринг Аут записываем до пробела
                if (flag && line.startsWith("400") || flag && line.startsWith("500")) { // если начинается с 400 или 500
                    flag = false; // Флаг = фолс
                    start = out[1]; // Стринг старт = Аут[1]
                } else if (!flag && line.startsWith("200") || line.startsWith("300")) { //если Флаг-фолс и нач с 200
                    flag = true; // флаг = тру
                    end = out[1]; // Стринг енд = Аут[1]
                    status.add(start + "; " + end); // добавляем в коллекцию Статус Старт + Енд
                }
            }
            try (PrintWriter write = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (String s : status) {
                    write.println(s); // записываем результаты Статус в Таргет
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.csv", "target.csv");
    }
}