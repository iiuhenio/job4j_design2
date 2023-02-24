package ru.job4j;

public class Ahaha {
    public static void main(String[] args) {
        args[0] = "one";
        args[1] = "two";
        args[2] = "three";
        for (int i = 0; i < args.length; i++) {
           System.out.println(i);
            System.out.println(args[i]);
        }
    }
}
