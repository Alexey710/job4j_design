package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {

    @Test
    public void add() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        simpleSet.add(1);
        simpleSet.add(2);
        Iterator<Integer> it = simpleSet.iterator();
        Assert.assertThat(it.next(), Is.is(1));
        Assert.assertThat(it.next(), Is.is(2));
        Assert.assertThat(simpleSet.simpleArray.getSize(), Is.is(2));


    }

    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        simpleSet.add(1);
        simpleSet.add(1);
        simpleSet.add(2);
        Iterator<Integer> it = simpleSet.iterator();
        it.next();
        it.next();
        it.next();
    }
}