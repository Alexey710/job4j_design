package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> found = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            bufferedReader.lines().forEach(s -> {
                String[] arr = s.split(" ");
                if (arr[arr.length - 2].equals("404")) {
                    found.add(s);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(printWriter :: write);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
        System.out.println(log);
    }
}