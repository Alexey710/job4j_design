package ru.job4j.collection.hashmap;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;

public class SimpleHashMapTest {

    @Test
    public void whenInsertDuplicate() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user1 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        User user2 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        User user3 = new User("Olga", 0, new GregorianCalendar(1950, Calendar.MAY, 20));
        User user4 = new User("Gena", 3, new GregorianCalendar(1988, Calendar.MAY, 20));
        Assert.assertThat(simpleHashMap.insert(user1, "first"), is(true));
        Assert.assertThat(simpleHashMap.insert(user2, "second"), is(false));
        Assert.assertThat(simpleHashMap.insert(user3, "third"), is(true));
        Assert.assertThat(simpleHashMap.insert(user4, "forth"), is(true));
        Assert.assertThat(simpleHashMap.getCount(), is(3));
    }

    @Test
    public void whenValidGet() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user1 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        User user3 = new User("Olga", 0, new GregorianCalendar(1950, Calendar.MAY, 20));
        User user4 = new User("Gena", 3, new GregorianCalendar(1988, Calendar.MAY, 20));
        simpleHashMap.insert(user1, "first");
        simpleHashMap.insert(user3, "third");
        simpleHashMap.insert(user4, "forth");
        Assert.assertThat(simpleHashMap.get(user1), is("first"));
        Assert.assertThat(simpleHashMap.get(user3), is("third"));
        Assert.assertThat(simpleHashMap.get(user4), is("forth"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenInvalidGet() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user2 = new User("Ivan2", 0, new GregorianCalendar(1000, Calendar.MAY, 21));
        simpleHashMap.get(user2);
    }

    @Test
    public void whenDeleteValidAndAfterDeleteInvalid() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user1 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        simpleHashMap.insert(user1, "first");
        Assert.assertTrue(simpleHashMap.delete(user1));
        Assert.assertFalse(simpleHashMap.delete(user1));

    }

    @Test
    public void whenIterator() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user1 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        User user3 = new User("Olga", 0, new GregorianCalendar(1950, Calendar.MAY, 20));
        User user4 = new User("Gena", 3, new GregorianCalendar(1988, Calendar.MAY, 20));
        simpleHashMap.insert(user1, "first");
        simpleHashMap.insert(user3, "third");
        simpleHashMap.insert(user4, "forth");
        Iterator<String> it = simpleHashMap.iterator();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextIsAbsent() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        Iterator<String> it = simpleHashMap.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorModificationException() {
        SimpleHashMap<User, String> simpleHashMap = new SimpleHashMap<>();
        User user1 = new User("Ivan", 2, new GregorianCalendar(1970, Calendar.MAY, 21));
        User user3 = new User("Olga", 0, new GregorianCalendar(1950, Calendar.MAY, 20));
        User user4 = new User("Gena", 3, new GregorianCalendar(1988, Calendar.MAY, 20));
        simpleHashMap.insert(user1, "first");
        simpleHashMap.insert(user3, "third");
        Iterator<String> it = simpleHashMap.iterator();
        simpleHashMap.insert(user4, "forth");
        it.next();
        it.next();
        it.next();
    }

}