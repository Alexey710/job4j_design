package ru.job4j.architecture.template;

import org.junit.Assert;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void produceExistingKey() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alexey");
        map.put("subject", "you");
        String rsl = generator.produce("I am a ${name}, Who are ${subject}? ", map);
        String expected = String.format("I am a %s, Who are %s?", "Alexey", "you");
        Assert.assertEquals(rsl, expected);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceNotExistingKey() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alexey");
        map.put("subject", "you");
        generator.produce("I am a ${surname}, Who are ${subject}? ", map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceExcessKey() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alexey");
        map.put("subject", "you");
        map.put("something", "you");
        generator.produce("I am a ${name}, Who are ${subject}? ", map);
    }
}