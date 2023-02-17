package ru.job4j.io;

import java.io.File;

public class Dir {

    public String[] args;

    public static void main(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (validation(args)) {
            System.out.println(String.format("size : %s", file.getTotalSpace()));
            for (File subfile : file.listFiles()) {
                if (subfile.getName().endsWith(args[1])) {
                    System.out.println(subfile.getName());
                }
            }
        }
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