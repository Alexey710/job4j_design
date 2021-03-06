package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private final List<String> interval = new ArrayList<>();
    private int mark;

    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            bufferedReader.lines().forEach(s -> {
                String[] arr = s.split(" ");
                if (arr[0].equals("500") && mark == 0 || arr[0].equals("400") && mark == 0) {
                    interval.add(arr[1]);
                    mark = 1;
                }
                if (arr[0].equals("200") && mark == 1 || arr[0].equals("300") && mark == 1) {
                    interval.add(arr[1]);
                    mark = 0;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        write(target);

    }

    private void write(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            int count = 0;
            int lastSemicolon  = 0;
            for (String s: interval) {
                lastSemicolon++;
                if (count == 1) {
                    out.write(";");
                } else if (count == 2) {
                    out.write(";");
                    out.write(System.lineSeparator());
                    count = 0;
                }
                out.write(s);
                if (lastSemicolon == interval.size()) {
                    out.write(";");
                }
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}