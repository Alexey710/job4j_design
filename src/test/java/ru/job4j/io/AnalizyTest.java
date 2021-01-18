package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTemporaryFolder() throws IOException {
        File source = folder.newFile("server_log.csv");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01"
                    + System.lineSeparator()
                    + "500 10:57:01"
                    + System.lineSeparator()
                    + "400 10:58:01"
                    + System.lineSeparator()
                    + "200 10:59:01"
                    + System.lineSeparator()
                    + "500 11:01:02"
                    + System.lineSeparator()
                    + "200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            bufferedReader.lines().forEach(result :: add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertThat(result.get(0), is("10:57:01;10:59:01;"));
        Assert.assertThat(result.get(1), is("11:01:02;11:02:02;"));
    }
}