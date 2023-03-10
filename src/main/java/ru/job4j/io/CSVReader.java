package ru.job4j.io;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class CSVReader {
    /**
     * Данный метод должен принимать файл, обрабатывать его и выводить содержимое на экран
     */
    public static void handle(ArgsName argsName) throws Exception {

        String[] filter = new String[]{"name", "age", "last_name", "education"};
        String[] names = new String[4];
        List<String> lines = new ArrayList<>();
        File source = new File(new File(argsName.get("path")).getAbsolutePath());
        File target = new File(new File(argsName.get("out")).getAbsolutePath());

        try (FileReader fileReader = new FileReader(source);
             var scan = new Scanner(fileReader).useDelimiter(",");
             Scanner scanner = new Scanner(fileReader)) {
             names = scan.next().replaceAll("\"", "").split(";");
               while (scan.hasNext()) {
                   lines.add(scan.next());
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

        public static void main(String[] args) throws Exception {
            ArgsName an = ArgsName.of(args);
            CSVReader.handle(an);
        }
}

