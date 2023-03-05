package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {

        try {
            FileOutputStream   fos = new FileOutputStream(target);
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte[] buffer = new byte[128];
            for (File currentFile : sources) {
                if (!currentFile.isDirectory()) {
                    ZipEntry entry = new ZipEntry(currentFile.getName());
                    FileInputStream fis = new FileInputStream(currentFile);
                    zos.putNextEntry(entry);
                    int read;
                    while ((read = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, read);
                    }
                    zos.closeEntry();
                    fis.close();
                }
            }
            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        List<File> sources;

        /*
         * Архивируем один файл:
         */
        zip.packSingleFile(
              new File("./pom.xml"),
              new File(argsName.get("o"))
        );

        /*
         * Добавляем в коллекцию все файлы из категории
         */
        File dir = new File(argsName.get("d"));
        File[] arrFiles = dir.listFiles();
        assert arrFiles != null;
        sources = Arrays.asList(arrFiles);

        /*
         * Выводим все что есть в коллекции сейчас:
         */
        System.out.println(sources.size());
        for (File file1 : sources) {
           System.out.println(file1);
        }

        /*
         * находим файлы по нужному предикату:
         */
        System.out.println("Находим файл:");
        Path file2 = Paths.get(argsName.get("d"));
        Search.search(file2, p -> p.toFile().getName().endsWith(argsName.get("e"))).forEach(System.out::println);

        /*
         * Смотрим, изменилась ли коллекция:
         */
        System.out.println("После удаления");
        for (File file1 : sources) {
            System.out.println(file1);
        }

        /*
         * Архивируем коллекцию:
         */
        zip.packFiles(sources, new File(argsName.get("o")));

    }
}