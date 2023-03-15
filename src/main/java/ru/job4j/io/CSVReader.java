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
            Scanner scanner = new Scanner(fileReader).useDelimiter(System.lineSeparator())) {
            String[] names = scanner.nextLine().replaceAll("\"", "").split(argsName.get("delimiter"));
            while (scanner.hasNext()) {
                lines.add(scanner.next());
            }

            int[] position = new int[names.length];
            Arrays.fill(position, -1);
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < filter.length; j++) {
                    if (Objects.equals(names[i], filter[j])) {
                        position[i] = j;
                    }
                }
            }
            System.out.println("filter = " + Arrays.toString(filter));
            System.out.println("names = " + Arrays.toString(names));
            System.out.println("lines = " + lines);
            System.out.println("position = " + Arrays.toString(position));

            String[] answer = new String[filter.length];
            String line = lines.get(0);
            String[] lines1 = line.replaceAll("\"", "").split(",");
            for (int i = 0; i < position.length; i++) {
                for (int j = 0; j < lines1.length; j++) {
                    if (position[i] != -1) {
                        answer[position[i]] == lines1[j];
                    }
                }
            }
            System.out.println("lines1 = " + Arrays.toString(lines1));
            System.out.println("answer = " + Arrays.toString(answer));

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

    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}