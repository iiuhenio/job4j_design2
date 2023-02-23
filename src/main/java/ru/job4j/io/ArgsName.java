package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    /**
     * Тут храним пары ключ-значение
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Тут получаем значение по ключу
     */
    public String get(String key) {

        return values.get(key);
    }

    /**
     * Тут переделываем массив args в значения
     */
    private void parse(String[] args) {
        /* TODO parse args to values. */
        System.out.println("Size: " + args.length);
        for (String arg : args) {
            String str = arg;
            StringBuilder sb = new StringBuilder(str);

            if (str.startsWith("-")) {
                sb.deleteCharAt(0);
                str = sb.toString();
            } else {
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

    /**
     * Тут мы создаем объект класса, вызывем у него метод parse() и возвращаем его
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName(); /* создаем объект класса */
        if (args.length == 0) {
            throw new IllegalArgumentException("There are no args");
        }
        names.parse(args); /* вызываем метод parse() */
        return names; /* возвращаем то что получилось, то есть значение */
    }

    /**
     * это просто метод Мейн
     */
    public static void main(String[] args) {
       ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
       System.out.println(jvm.get("Xmx"));

       ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
       System.out.println(zip.get("out"));

        ArgsName zip1 = ArgsName.of(new String[] {"-out=", "-encoding="});
        System.out.println(zip1.get("out"));
    }
}