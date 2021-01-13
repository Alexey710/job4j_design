package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    static String computeTable() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            stringBuilder.append(System.lineSeparator());
            for (int j = 1; j <= 9; j++) {
                stringBuilder.append(i * j);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    static void outputToFile() {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(computeTable().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        outputToFile();
    }
}
