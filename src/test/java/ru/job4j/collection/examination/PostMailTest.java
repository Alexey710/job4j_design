package ru.job4j.collection.examination;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;

public class PostMailTest {

    @Test
    public void whenCombineFiveToTwo() {
        PostMail postMail = new PostMail();
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1",
                new HashSet<String>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        input.put("user2",
                new HashSet<String>(Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        input.put("user3",
                new HashSet<String>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        input.put("user4",
                new HashSet<String>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        input.put("user5",
                new HashSet<String>(Arrays.asList("xyz@pisem.net")));
        Map<String, Set<String>> rsl = postMail.combine(input);
        Assert.assertThat(rsl.get("user3"),
                is(Set.of("vasya@pupkin.com", "xyz@pisem.net")));
        Assert.assertThat(rsl.get("user4"),
                is(Set.of("aaa@bbb.ru", "ups@pisem.net",
                        "lol@mail.ru", "xxx@ya.ru", "foo@gmail.com")));
        Assert.assertTrue(rsl.size() == 2);
    }

    @Test
    public void whenCombineFiveToOne() {
        PostMail postMail = new PostMail();
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1", new HashSet<String>(Arrays.asList("ups@pisem.net")));
        input.put("user2", new HashSet<String>(Arrays.asList("xyz@pisem.net", "ups@pisem.net")));
        input.put("user3", new HashSet<String>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        input.put("user4", new HashSet<String>(Arrays.asList("ups@pisem.net")));
        input.put("user5", new HashSet<String>(Arrays.asList("ups@pisem.net")));
        Map<String, Set<String>> rsl = postMail.combine(input);
        Assert.assertThat(rsl.get("user4"), is(Set.of("ups@pisem.net",
                "vasya@pupkin.com", "xyz@pisem.net")));
        Assert.assertTrue(rsl.size() == 1);
    }

    @Test
    public void whenCombineCanNotCombineForOneTime() {
        PostMail postMail = new PostMail();
        Map<String, Set<String>> input = new HashMap<>();
        input.put("user1",
                new HashSet<String>(Arrays.asList("a@pisem.net")));
        input.put("user2",
                new HashSet<String>(Arrays.asList("c@pisem.net", "b@pisem.net")));
        input.put("user3",
                new HashSet<String>(Arrays.asList("a@pisem.net", "c@pisem.net")));
        input.put("user4",
                new HashSet<String>(Arrays.asList("g@pisem.net")));
        input.put("user5",
                new HashSet<String>(Arrays.asList("g@pisem.net", "c@pisem.net")));
        Map<String, Set<String>> rsl = postMail.combine(input);
        Assert.assertThat(rsl.get("user4"),
                is(Set.of("g@pisem.net", "c@pisem.net", "b@pisem.net", "a@pisem.net")));
        Assert.assertTrue(rsl.size() == 1);
    }
}