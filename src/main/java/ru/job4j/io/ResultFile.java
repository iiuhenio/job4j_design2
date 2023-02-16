package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * В первом блоке курса было задание на вывод таблицы умножения на консоль.
 * Вам нужно записать результат вычисления в файл.
 *
 *  Напишем программу, которая записывает текст в файл
 *  Класс java.io.FileOutputStream позволяет записать данные в файл
 */
public class ResultFile {
    public static void main(String[] args) {

        /*
         * конструктор класса FileOutputStream принимает имя файла.
         * Файл будет создан в корне проекта
         * Любой ресурс должен быть закрыт, по этому используется конструкция try-with-resources
         */
        try (FileOutputStream out = new FileOutputStream("data/result.txt")) {

            /*
             * для записи используется метод out.write
             * этот метод принимает массив байт.
             * по этому строку преобразовали в массив байтов
             */
            int[][] array = new int[10][10];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = (i + 1) * (j + 1);
                    int x = array[i][j];
                    String y = Integer.toString(x);
                    out.write(y.getBytes());
                }
                /*
                 * Обратите внимание, что для перехода на новую строку используется System.lineSeparator().
                 * Это сделано специально, поскольку переход на новую строку зависит от операционной системы.
                 * В какой-то \n, в какой-то \r\n. Использование System.lineSeparator() позволяет
                 * сделать код независимым от операционной системы.
                 */
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}