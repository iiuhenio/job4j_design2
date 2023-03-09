package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    /**
     * Данный метод должен принимать файл, обрабатывать его и выводить содержимое на экран
     */
    public static void handle(ArgsName argsName) throws Exception {
        File source = new File(new File(argsName.get("path")).getAbsolutePath());
        File target = new File(new File(argsName.get("out")).getAbsolutePath());
        try (FileReader fileReader = new FileReader(source);
             var scan = new Scanner(fileReader).useDelimiter(",");
             Scanner scanner = new Scanner(fileReader)) {
            while (scan.hasNext()) {
                if (Objects.equals(argsName.get("out"), "stdout")) {
                    System.out.print(scan.next());
                }
                if (!Objects.equals(argsName.get("out"), "stdout")) {
                    try (BufferedOutputStream out =
                                 new BufferedOutputStream(new FileOutputStream(target))) {
                        out.write(scanner.next().getBytes());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}
