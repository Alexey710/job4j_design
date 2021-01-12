package ru.job4j.collection.examination;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

public class AnalizeTest {

    @Test
    public void whenDiff() {
        Analize analize = new Analize();
        Analize.User user0 = new Analize.User(0, "Ivan");
        Analize.User user1 = new Analize.User(1, "Stepan");
        Analize.User user2 = new Analize.User(2, "Evgeniy");
        Analize.User user10 = new Analize.User(1, "Someone");
        Analize.User user20 = new Analize.User(2, "Someone");
        Analize.User user3 = new Analize.User(3, "Vitaliy");
        List<Analize.User> before = List.of(user0, user1, user2);
        List<Analize.User> after = List.of(user10, user20, user3);
        Analize.Info rsl = analize.diff(before, after);
        Assert.assertThat(rsl.getAdded(), is(1));
        Assert.assertThat(rsl.getDeleted(), is(1));
        Assert.assertThat(rsl.getChanged(), is(2));
    }

    @Test
    public void whenDiffNothingChanged() {
        Analize analize = new Analize();
        Analize.User user0 = new Analize.User(0, "Ivan");
        Analize.User user1 = new Analize.User(1, "Stepan");
        List<Analize.User> before = List.of(user0, user1);
        List<Analize.User> after = List.of(user0, user1);
        Analize.Info rsl = analize.diff(before, after);
        Assert.assertThat(rsl.getAdded(), is(0));
        Assert.assertThat(rsl.getDeleted(), is(0));
        Assert.assertThat(rsl.getChanged(), is(0));
    }

    @Test
    public void whenDiffChangedOneNameOnly() {
        Analize analize = new Analize();
        Analize.User user0 = new Analize.User(0, "Ivan");
        Analize.User user1 = new Analize.User(1, "Stepan");
        Analize.User user10 = new Analize.User(1, "Stepan2");
        List<Analize.User> before = List.of(user0, user1);
        List<Analize.User> after = List.of(user0, user10);
        Analize.Info rsl = analize.diff(before, after);
        Assert.assertThat(rsl.getAdded(), is(0));
        Assert.assertThat(rsl.getDeleted(), is(0));
        Assert.assertThat(rsl.getChanged(), is(1));
    }

    @Test
    public void whenDiffDeleted() {
        Analize analize = new Analize();
        Analize.User user0 = new Analize.User(0, "Ivan");
        Analize.User user1 = new Analize.User(1, "Stepan");
        List<Analize.User> before = List.of(user0, user1);
        List<Analize.User> after = List.of(user0);
        Analize.Info rsl = analize.diff(before, after);
        Assert.assertThat(rsl.getAdded(), is(0));
        Assert.assertThat(rsl.getDeleted(), is(1));
        Assert.assertThat(rsl.getChanged(), is(0));
    }
}