package ru.job4j.assertj;

public class SimpleModel {
    private String name = "";

    public String getName() {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    public void setName(String word, int number) {
        if (word.length() != number) {
            throw new IllegalArgumentException(
                    String.format("this word: %s has length not equal to : %s", word, number)
            );
        }
        this.name = word;
    }

    public static void main(String[] args) {
        SimpleModel sm = new SimpleModel();
        sm.setName("test", 4);
        System.out.println("Длина строки: " + sm.getName().length());
        System.out.println("Строка: " + sm.getName());
    }
}