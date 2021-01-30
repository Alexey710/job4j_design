package ru.job4j.sql.jdbc.ddl;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String driver = (String) properties.get("jdbc.connection.driver_class");
        String url = (String) properties.get("jdbc.connection.url");
        String user = (String) properties.get("jdbc.connection.username");
        String password = (String) properties.get("jdbc.connection.password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Properties getProperties() {
        return properties;
    }

    public void createTable(String tableName) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table %s;",
                        tableName
                );
                statement.execute(sql);
            }
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "drop table %s;",
                        tableName
                );
                statement.execute(sql);
            }
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE %s ADD COLUMN %s %s;",
                        tableName,
                        columnName,
                        type
                );
                statement.execute(sql);
            }
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE %s DROP COLUMN %s",
                        tableName,
                        columnName
                );
                statement.execute(sql);
            }
        }
    }

    public void renameColumn(
            String tableName, String columnName, String newColumnName) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "ALTER TABLE %s RENAME %s TO %s;",
                        tableName,
                        columnName,
                        newColumnName
                );
                statement.execute(sql);
            }
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(".\\jdbc.properties");) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("table_two");
            tableEditor.dropTable("table_two");
            tableEditor.addColumn("table_one", "age", "int");
            tableEditor.dropColumn("table_one", "id");
            tableEditor.renameColumn("table_one", "age", "id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}