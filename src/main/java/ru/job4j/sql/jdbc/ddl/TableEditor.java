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

    private void doQuery(String sql) throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        }
    }

    public void createTable(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("create table %s (%s %s);",
                tableName, columnName, type);
        doQuery(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s;", tableName);
        doQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        doQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        doQuery(sql);
    }

    public void renameColumn(
            String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME %s TO %s;", tableName, columnName, newColumnName);
        doQuery(sql);
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
            tableEditor.createTable("table_two", "name", "varchar(255)");
            tableEditor.addColumn("table_two", "age", "int");
            tableEditor.renameColumn("table_two", "age", "id");
            tableEditor.dropColumn("table_two", "id");
            tableEditor.dropTable("table_two");
            System.out.println(tableEditor.getScheme("table_two"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}