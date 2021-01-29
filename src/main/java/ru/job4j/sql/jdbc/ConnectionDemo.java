package ru.job4j.sql.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private String login;
    private String password;

    void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        File init = new File(".\\app.properties");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(init))) {
            bufferedReader.lines().forEach(p -> {
                String[] arr = p.split("=");
                if (arr[0].equals("jdbc.connection.username")) {
                    login = arr[1];
                }
                if (arr[0].equals("jdbc.connection.password")) {
                    password = arr[1];
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ConnectionDemo().connect();
    }
}
