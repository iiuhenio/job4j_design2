package ru.job4j;

public class Ahahah {
    /**
     * Это решение:
     */
    public int summaMassiva(int[] array) {
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
        int i = ah.summaMassiva(new int[]{1, 2, 3, 4});
        System.out.println(i);
    }
}
