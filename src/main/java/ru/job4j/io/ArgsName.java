package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("This value does not exist");
        }
      return rsl;
    }

    private void parse(String[] args) {
        for (String str : args) {

            StringBuilder sb = new StringBuilder(str);

            if (!str.contains("=")) {
                throw new IllegalArgumentException("There is no equal sign");
            }
            if (!str.startsWith("-")) {
                throw new IllegalArgumentException("There is no line");
            }
            if (str.startsWith("-")) {
                sb.deleteCharAt(0);
                str = sb.toString();
            }

            String[] args2 = str.split("=", 2);

            if (args2.length != 2) {
                throw new IllegalArgumentException("There should be two args");
            }
            if (args2[0].isEmpty()) {
                throw new IllegalArgumentException("There is no key");
            }
            if (args2[1].isEmpty()) {
                throw new IllegalArgumentException("There is no value");
            }

            values.put(args2[0], args2[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length == 0) {
            throw new IllegalArgumentException("There are no args");
        }
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
       ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
       System.out.println(jvm.get("Xmx"));

       ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
       System.out.println(zip.get("out"));

        ArgsName zip1 = ArgsName.of(new String[] {"-out=", "-encoding="});
        System.out.println(zip1.get("out"));
    }
}