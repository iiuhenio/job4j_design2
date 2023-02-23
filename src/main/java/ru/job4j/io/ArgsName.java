package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        ArgsName an = new ArgsName();
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This value does not exist");
        }
      return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String str = arg;
            StringBuilder sb = new StringBuilder(str);

            if (str.startsWith("-")) {
                sb.deleteCharAt(0);
                str = sb.toString();
            } else {
                throw new IllegalArgumentException("There is no line");
            }
            if (!str.contains("=")) {
                throw new IllegalArgumentException("There is no equal sign");
            }

            String[] args2 = str.split("=", 2);
            values.put(args2[0], args2[1]);

            if (args2[0].isEmpty()) {
                throw new IllegalArgumentException("There is no key");
            }
            if (args2[1].isEmpty()) {
                throw new IllegalArgumentException("There is no value");
            }
        }
        System.out.println("Сейчас в мапе вот что: ");
        values.forEach((key, value) -> System.out.println(key + " : " + value));
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