package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите любой символ для начала чата");
        String a = console.nextLine();
        System.out.println("Чат начался!");
        while (!Objects.equals(a, "Some phrase")) {
            a = console.nextLine();
            System.out.println(ConsoleChat.readPhrases().get((int) (Math.random() * 4)));
            if (Objects.equals(a, STOP)) {
                System.out.println("Сейчас я буду молчать");
                while (!Objects.equals(a, CONTINUE)) {
                    a = console.nextLine();
                    if (!Objects.equals(a, OUT)) {
                        break;
                    }
                }
            }
            if (Objects.equals(a, CONTINUE)) {
                System.out.println("Продолжаем чат!");
            }
            if (Objects.equals(a, OUT)) {
                break;
            }
        }
        System.out.println("Чат завершен");
    }

    private static List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        String path1 = "./src/data/text.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
            while (br.ready()) {
                rsl.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("./src/data/log.txt"));
        System.setOut(out);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/text.txt");
        cc.run();
    }
}