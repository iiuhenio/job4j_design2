package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * В этом уроке мы поговорим про JDBC.
 * JDBC – это API, т.е. набор вспомогательных классов, которое позволяет работать с базами данных.
 * Причем JDBC предоставляет единый интерфейс для работы с ними, ведь бывают различные базы данных.
 */
public class ConnectingDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("data/app2.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}