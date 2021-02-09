package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutCommentAndWithComment() {
        Config config = new Config(".\\app.properties");
        config.load();

        assertThat(config.value("jdbc.connection.username"), is("postgres"));
        assertThat(config.value("jdbc.connection.password"), is("postgres"));
        assertNull(config.value("comment"));
    }

}