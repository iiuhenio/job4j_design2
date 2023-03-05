package ru.job4j;

public class Ahahah {
    /**
     * Это решение:
     */
    public int task(int[] array) {
        int summa = 0;

        for (int i = 0; i < array.length; i++) {
            summa = summa + array[i];
        }
        return summa;
    }

    /**
     * Это проверка:
     */
    public static void main(String[] args) {
        Ahahah ah = new Ahahah();
        int i = ah.task(new int[]{1, 2, 3, 4});
        System.out.println(i);
    }

    public static void createElement() {

    }

    public static int task(int x) {
        int id = x;
        
        return x;
    }
}
