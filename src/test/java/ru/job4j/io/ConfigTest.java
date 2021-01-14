package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = ".\\app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenPairWithComment() {
        String path = ".\\app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql:"));
    }
}