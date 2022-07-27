package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.FileInputStream;

/**
 * 1. Создайте в корне проекта файл even.txt.
 * 2. В файл добавьте числа. Одно число  на строку.
 * 3. Создайте класс ru.job4j.io.EvenNumberFile с методом main.
 * 4. В классе нужно прочитать файл even.txt.
 * 5. Для каждого числа проверить является ли оно четным числом. Ответ вывести на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int i = Integer.parseInt(line);
                if (i % 2 == 0) {
                    System.out.println(i + " - четное число");
                } else {
                    System.out.println(i + " - нечетное число");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}