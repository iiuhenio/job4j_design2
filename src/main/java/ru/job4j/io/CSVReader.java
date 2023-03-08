package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    /**
     * Данный метод должен принимать файл, обрабатывать его и выводить содержимое на экран
     */
    public static void handle(ArgsName argsName) throws Exception {
        try (FileReader fileReader = new FileReader("info.txt");
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNext()) {
                System.out.print(scanner.nextInt());
                System.out.println(scanner.next());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName an = new ArgsName();
        System.out.println(an.get("path"));

        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
    }
}
