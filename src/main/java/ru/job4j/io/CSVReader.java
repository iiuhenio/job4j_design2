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

        /*
         *  Ниже объявлены:
         * filter[] - по каким словам фильтруем
         * lines - поледние строчки файла
         * файлы откуда читаем и куда записываем
         */
        String[] filter = argsName.get("filter").split(",");
        List<String> lines = new ArrayList<>();
        File source = Path.of(argsName.get("path")).toFile();
        File target = Path.of(argsName.get("out")).toFile();

        List<String> lastLines = new ArrayList<>();

        System.out.println(Arrays.toString(filter));

        /*
         *  names[] - все возможные фильтры
         */
        try (FileReader fileReader = new FileReader(source);
            var scan = new Scanner(fileReader).useDelimiter(",");
            Scanner scanner = new Scanner(fileReader).useDelimiter(System.lineSeparator())) {
            String[] names = scanner.nextLine().split(argsName.get("delimiter"));
            while (scanner.hasNext()) {
                lines.add(scanner.next());
            }

            /*
             * position[] - какие столбцы присутствуют в заданном фильтре и их индекс
             */
            int[] position = new int[names.length];
            Arrays.fill(position, -1);
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < filter.length; j++) {
                    if (Objects.equals(names[i], filter[j])) {
                        position[i] = j;
                    }
                }
            }

            /*
             * lines1[] - вся первая строка
             * answer[] - отфильтрованные строки (из первой(второй) строки)
             */
            String[] answer = new String[filter.length];
            for (int j = 0; j < lines.size(); j++) {
                String line = lines.get(j);
                String[] lines1 = line.split(argsName.get("delimiter"));
                for (int i = 0; i < position.length; i++) {
                    if (position[i] != -1) {
                        answer[position[i]] = lines1[i];
                    }
                }
                String answer1 = Arrays.toString(answer);
                lastLines.add(answer1);

                /*
                System.out.println("В массивах сейчас: ");
                System.out.println("filter = " + Arrays.toString(filter));
                System.out.println("names = " + Arrays.toString(names));
                System.out.println("lines = " + lines);
                System.out.println("position = " + Arrays.toString(position));
                System.out.println("lines1 = " + Arrays.toString(lines1));
                System.out.println("answer = " + Arrays.toString(answer));
                System.out.println("answer1 = " + answer1);
                */
            }

            if (Objects.equals(argsName.get("out"), "stdout")) {
                System.out.println(lastLines);
            }
            if (!Objects.equals(argsName.get("out"), "stdout")) {
                try (BufferedOutputStream out =
                             new BufferedOutputStream(new FileOutputStream(target))) {
                    out.write(scan.next().getBytes());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}
