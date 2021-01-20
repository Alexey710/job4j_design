package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String log;
    private final String botAnswers;

    public ConsoleChat(String log, String botAnswers) {
        this.log = log;
        this.botAnswers = botAnswers;
    }

    private void chatLogger(String path, String data) {
            try (BufferedWriter br = new BufferedWriter(
                    new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
                br.write(data + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    public void run() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(answers :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String input;
        while (!(input = scanner.nextLine()).equals(OUT)) {
            chatLogger(log, input);
            if (input.equals(STOP)) {
                while (!input.equals(CONTINUE)) {
                    input  = scanner.nextLine();
                    chatLogger(log, input);
                }
            }
            int index = random.nextInt(answers.size());
            String bot = answers.get(index);
            System.out.println(bot);
            chatLogger(log, bot);
        }
        chatLogger(log, OUT);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\bot_log.txt",
                "C:\\projects\\job4j_design\\bot_answers.txt");
        cc.run();
    }
}