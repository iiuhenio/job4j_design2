package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        Scanner console = new Scanner(System.in);
        System.out.println("Введите любой символ для начала чата");
        String a = console.nextLine();
        log.add(a);
        System.out.println("Чат начался!");
        while (!Objects.equals(a, OUT)) {
            if (Objects.equals(a, STOP)) {
                System.out.println("Сейчас я буду молчать");
                while (!Objects.equals(a, CONTINUE)) {
                    a = console.nextLine();
                    log.add(a);
                }
            }
            String str = cc.readPhrases().get((int) (Math.random() * 4));
            System.out.println(str);
            log.add(str);
            a = console.nextLine();
            log.add(a);
        }
        System.out.println("Чат завершен");
        cc.saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            while (br.ready()) {
                rsl.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintStream pw = new PrintStream(new FileOutputStream(path))) {
            for (String str : log) {
                pw.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/text.txt");
        cc.run();
    }
}