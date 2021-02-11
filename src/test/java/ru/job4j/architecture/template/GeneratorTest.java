package ru.job4j.architecture.template;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {
    private final Generator generator = new Generator() {
        @Override
        public String produce(String template, Map<String, String> args) {
            return null;
        }
    };
    private static final Map<String, String> map = new HashMap<>();

    @BeforeClass
    public static void beforeClass() throws Exception {
        map.put("name", "Alexey");
        map.put("subject", "you");
    }

    @Test
    public void produceExistingKey() {
        String rsl = generator.produce("I am a ${name}, Who are ${subject}? ", map);
        String expected = String.format("I am a %s, Who are %s?", "Alexey", "you");
        Assert.assertEquals(rsl, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceNotExistingKey() {
        Map<String, String> map = new HashMap<>();
        generator.produce("I am a ${surname}, Who are ${subject}? ", map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceExcessKey() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "Alexey");
        map1.put("subject", "you");
        map1.put("something", "you");
        generator.produce("I am a ${name}, Who are ${subject}? ", map1);
    }
}