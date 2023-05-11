package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        properties.setProperty("driver", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/idea_db");
        properties.setProperty("login", "postgres");
        properties.setProperty("password", "password");

        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, login, password);

        /* connection = null; */

    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        try (Connection connection = initConnection();) {
            try {
                assert connection != null;
                try (Statement statement = connection.createStatement()) {
                    String sql = String.format(
                            "create table if not exists demo_table(%s, %s);",
                            "id serial primary key",
                            "name text"
                    );
                    statement.execute(sql);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void dropTable(String tableName) {
    }

    public void addColumn(String tableName, String columnName, String type) {
    }

    public void dropColumn(String tableName, String columnName) {
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
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

    public static void main(String[] args) throws Exception {
        TableEditor te = new TableEditor(new Properties());

        te.initConnection();
        te.createTable("table1");
        System.out.println(te.getTableScheme("table1"));
    }
}