package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {

    static void evenDetectedFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            while ((read = fileInputStream.read()) != -1) {
                stringBuilder.append((char) read);
            }
            String result = stringBuilder.toString();
            String[] data = result.split(System.lineSeparator());
            for (String elem : data) {
                if (Integer.parseInt(elem) % 2 == 0) {
                    System.out.println(elem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        evenDetectedFromFile();
    }
}
