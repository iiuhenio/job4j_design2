package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * В этом уроке нужно написать программу, которая ищет дубликаты файлов в папке.
 * Дубликаты – это два файла с одинаковым именем и размером. Программа должна искать дубликаты файлов в подпапках.
 * Результат нужно выводить на консоль.
 * Имя файла, Размер.
 *   -- Каталоги расположения дубликатов.
 *   Например, для выхода всех файлов в консоль мы можем написать такой короткий код
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> mapFiles = new HashMap<>();
    HashSet<Path> hs = new HashSet<>();
    Map<FileProperty, List<Path>> listMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(Files.size(file), file.getFileName().toString());
        if (mapFiles.containsKey(fileProp)) {
            print(file);
        } else {
            mapFiles.put(new FileProperty(Files.size(file), file.getFileName().toString()), file);
        }
        return super.visitFile(file, attrs);
    }

    public void print(Path file) throws IOException {
        FileProperty fileProp = new FileProperty(Files.size(file), file.getFileName().toString());
        System.out.println(mapFiles.get(fileProp).toAbsolutePath());
        System.out.println(file.toAbsolutePath());
    }
}