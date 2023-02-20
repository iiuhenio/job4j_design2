package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Разработайте программу Search, которая будет искать файлы только по определенному предикату
 */

public class Search {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        if (validation(args)) {
            search(file, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean validation(String[] args) {
        boolean rsl = true;
        File file = new File(args[0]);
        if (!file.isDirectory() || !file.exists()
                || !args[1].startsWith(".") || args[1].length() < 2
                || args.length != 2) {
            rsl = false;
        }
        return rsl;
    }
}