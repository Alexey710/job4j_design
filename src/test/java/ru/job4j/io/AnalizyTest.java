package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

public class AnalizyTest {

    @Test
    public void unavailable() {
        String source = "server_log.csv";
        String target = "unavailable.csv";
        new Analizy().unavailable(source, target);
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