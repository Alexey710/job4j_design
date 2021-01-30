package ru.job4j.sql.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    void connect() throws ClassNotFoundException, SQLException {
        String url;
        String user;
        String password;
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(".\\app.properties");) {
            String driver = (String) properties.get("jdbc.connection.driver_class");
            properties.load(fis);
            url = (String) properties.get("jdbc.connection.url");
            user = (String) properties.get("jdbc.connection.username");
            password = (String) properties.get("jdbc.connection.password");
            DatabaseMetaData metaData;
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                metaData = connection.getMetaData();
            }
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ConnectionDemo().connect();
    }
}
