package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

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

    public void run() {
        List<String> answers = new ArrayList<>();
        List<String> dialog = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(answers :: add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String input;
        while (!(input = scanner.nextLine()).equals(OUT)) {
            dialog.add(input);
            if (input.equals(STOP)) {
                while (!input.equals(CONTINUE)) {
                    input  = scanner.nextLine();
                    dialog.add(input);
                }
            }
            int index = random.nextInt(answers.size());
            String bot = answers.get(index);
            System.out.println(bot);
            dialog.add(bot);
        }
        dialog.add(OUT);
        try (PrintWriter br = new PrintWriter(
                new FileWriter(log, Charset.forName("WINDOWS-1251"), true))) {
            dialog.forEach(br::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\bot_log.txt",
                ".\\bot_answers.txt");
        cc.run();
    }
}