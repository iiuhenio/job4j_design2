package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Регулярные выражения — тема, которую программисты, даже опытные, зачастую откладывают на потом.
 * Однако большинству Java-разработчиков рано или поздно придётся столкнуться с обработкой текстовой информации.
 * Чаще всего — с операциями поиска в тексте и редактированием.
 * Без рег-х выражений прод-йи компактный программный код,связанный с обработкой текстов,попросту немыслим.
 * Так что хватит откладывать, разберёмся с «регулярками» прямо сейчас. Это не такая уж и сложная задача.
 */
public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("1");
        String text = "1231 и 1232 и 1233";
        Matcher matcher = pattern.matcher(text);
        String rsl = matcher.replaceAll("Job4j");
        System.out.println(rsl);

        Pattern pattern1 = Pattern.compile("11");
        String text1 = "111111";
        Matcher matcher1 = pattern1.matcher(text1);
        while (matcher1.find()) {
            System.out.println("Найдено совпадение: " + matcher1.group());
        }

        /*
         * Регулярное выражение "\\D+" значит "любой нецифровой символ от одного раза".
         */
        String str = "123+=-456:/789";
        String[] rsl2 = str.split("\\D+");
        System.out.println(Arrays.toString(rsl2));

        /*
         * Допустим, нам нужно найти в тексте дату, записанную в виде "дд.мм.гггг".
         * Чтобы создать корректный шаблон для поиска, нужно разделить данную дату на последовательности символов
         * (из чего состоит). В нашем случае это 2 цифры, точка, 2 цифры, точка, 4 цифры.
         */
        Pattern pattern4 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text4 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher4 = pattern4.matcher(text4);
        while (matcher4.find()) {
            System.out.println("Найдено совпадение: " + matcher4.group());
        }

        Pattern pattern5 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text5 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher5 = pattern5.matcher(text5);
        while (matcher5.find()) {
            System.out.println("Найдено совпадение: " + matcher5.group());
        }
    }
}