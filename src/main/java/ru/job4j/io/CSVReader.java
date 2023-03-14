package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.sql.Array;
import java.util.*;

public class CSVReader {
    /**
     * Данный метод должен принимать файл, обрабатывать его и выводить содержимое на экран
     */
    public static void handle(ArgsName argsName) throws Exception {

        String[] filter = argsName.get("filter").split(",");
        List<String> lines = new ArrayList<>();
        File source = Path.of(argsName.get("path")).toFile();
        File target = Path.of(argsName.get("out")).toFile();

        try (FileReader fileReader = new FileReader(source);
             /* var scan = new Scanner(fileReader).useDelimiter(","); */
             Scanner scanner = new Scanner(fileReader)) {
             String[] names = scanner.nextLine().replaceAll("\"", "").split(argsName.get("delimiter"));
             while (scanner.hasNext()) {
                lines.add(scanner.next());
             }

            Integer[] position = new Integer[names.length];
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < filter.length; j++) {
                    if (Objects.equals(names[i], filter[j])) {
                        position[i] = i;
                    }
                }

                System.out.println("Массив filter: ");
                for (String str1 : filter) {
                    System.out.println(str1);
                }
                System.out.println("Массив names: ");
                for (String str1 : names) {
                    System.out.println(str1);
                }
                System.out.println("Массив lines: ");
                for (String str1 : lines) {
                    System.out.println(str1);
                }
                System.out.println("Массив position: ");
                for (Integer str1 : position) {
                    System.out.println(str1);
                }

                /*
                if (Objects.equals(argsName.get("out"), "stdout")) {
                    System.out.print(scan.next());
                }
                if (!Objects.equals(argsName.get("out"), "stdout")) {
                    try (BufferedOutputStream out =
                                 new BufferedOutputStream(new FileOutputStream(target))) {
                        out.write(scan.next().getBytes());
                    }
                }
            }
                 */
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}