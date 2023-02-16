package ru.job4j.io;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenConfigHasEmptyLines() {
        String path = "./data/pair_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Ignore
    @Test
    public void whenPairWithMissingValue() {
        String path = "./data/pair_with_missing_value.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("hibernate.dialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPairWithoutEqualSign() {
        String path = "./data/pair_without_equal_sign.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("hibernate.connection.url"));
    }

    @Ignore
    @Test
    public void whenPairWithPluralEqualSign() {
        String path = "./data/pair_with_plural_equal_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"), is("jdbc:=postgresql://127.0.0.1:5432/trackstudio="));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void equalSignOnlyTest() {
        String path = "./data/pair_only_equal_sign.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noValueTest() {
        String path = "./data/pair_with_no_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noKeyTest() {
        String path = "./data/pair_no_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noKeyT3est() {
        String path = "./data/pair_no_key.properties";
        Config config = new Config(path);
        config.load();
    }
}