package ru.job4j.sql.examination;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

public class Cache2Test {
    private final Cache2 cache = new Cache2(Path.of("C:/test/cache"));

    @Ignore
    @Test
    public void whenSearchInCacheFirstTime() throws IOException {
        String rsl = cache.searchInCache("Names.txt");
        String expected = "Текст из файла Names.txt";
        Assert.assertEquals(rsl, expected);
    }

}