package ru.job4j.io;

import javax.imageio.IIOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * В этом задании нужно реализовать класс Config.
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Реализуйте метод load() по аналогии с методом toString().
     * Метод load() - должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
     *
     * Для считывания файлов нужно использовать
     *
     * import java.io.BufferedReader;
     * import java.io.FileReader;
     *
     * создаем буффер
     * пока в буффере есть данные (true)
     * считываем строку (readLine()), удяля пробелы в начале и в конце строки (trim())
     * indexOf класса String возвращает индекс первого вхождения искомого элемента
     * subString() возвращает симолвы, находящиеся в интервале задаваемых индексов
     */
    public void load() throws UnsupportedOperationException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while (reader.ready()) {
                String param = reader.readLine().trim();
                if (param.startsWith("=")
                        || param.endsWith("=")
                        || param.length() == 1) {
                       throw new IllegalArgumentException();
                   }
                if (!"".equals(param) && !param.startsWith("#") && param.contains("=")) {
                    int delimPos = param.indexOf("=");
                    if (delimPos > 0 && delimPos < param.length() - 1) {
                        String name = param.substring(0, delimPos);
                        String value = param.substring(delimPos + 1);
                        values.put(name, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Реализуйте метод value(String key) он должен возвращать значение ключа.
     */
    public String value(String key) {
        return values.get(key);
    }

    /**
     * Давайте переопределим метод toString() и выведем все содержимое файла.
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config cfg = new Config("./data/app.properties");
        System.out.println(cfg);
        cfg.load();
    }
}