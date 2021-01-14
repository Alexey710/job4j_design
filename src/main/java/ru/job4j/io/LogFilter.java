package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            bufferedReader.lines().forEach(list :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> found = new ArrayList<>();
        for (String s: list) {
            String[] arr = s.split(" ");
            if (arr[arr.length - 2].equals("404")) {
                found.add(s);
            }
        }
        return found;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}