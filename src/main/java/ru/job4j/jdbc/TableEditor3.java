package ru.job4j.jdbc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor3 implements AutoCloseable {

    /**
     * Файл connection
     */
    private Connection connection;

    /**
     * файл Properties
     */
    private Properties properties;

    /**
     * Конструктор класса TableEditor
     * почему в конструкторе вызывается метов?
     * @param properties
     */
    public TableEditor3(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /**
     * что Делает этот метод?
     */
    private void initConnection() {
        try (InputStream in = TableEditor3.class.getClassLoader().getResourceAsStream("app3.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            DriverManager.getConnection(url, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        /* connection = null; */
    }

    public void createTable(String tableName) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists (%s);",
                        tableName
                );
                statement.execute(sql);
                System.out.println(getTableScheme(tableName));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table (%s);",
                    tableName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE (%s) ADD COLUMN (%s) (%s) NULL;",
                        tableName,
                        columnName,
                        type
                );
                statement.execute(sql);
                System.out.println(getTableScheme(tableName));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE (%s) DROP COLUMN (%s);",
                    tableName,
                    columnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE (%s) RENAME COLUMN (%s) TO (%s);",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        File file = new File("c:/data.properties");
        InputStream in = TableEditor3.class.getClassLoader().getResourceAsStream("app3.properties");
        Properties pr = new Properties();
        pr.

        te.createTable("fff");
    }
}