package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.CoreMatchers.is;

public class SimpleArrayTest {

    private SimpleArray<Integer> simpleArray;

    @Before
    public void setUp() throws Exception {
        simpleArray = new SimpleArray<Integer>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
    }

    @Test
    public void whenAdd() {
        simpleArray.add(4);
        Assert.assertThat(simpleArray.get(3), is(4));
    }

    @Test
    public void whenSet() {
        simpleArray.set(0, 10);
        Assert.assertThat(simpleArray.get(0), is(10));
    }

    @Test
    public void whenGet() {
        Assert.assertThat(simpleArray.get(0), is(1));
        Assert.assertThat(simpleArray.get(1), is(2));
        Assert.assertThat(simpleArray.get(2), is(3));
        Assert.assertNull(simpleArray.get(3));
    }

    @Test
    public void whenRemove() {
        simpleArray.remove(0);
        Assert.assertThat(simpleArray.get(0), is(2));
        Assert.assertThat(simpleArray.get(1), is(3));
    }

    @Test
    public void whenIterator() {
        Iterator it = simpleArray.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(it.next(), is(1));
        Assert.assertThat(it.next(), is(2));
        Assert.assertThat(it.next(), is(3));
        Assert.assertNull(it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorException() {
        Iterator it = simpleArray.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(it.next(), is(1));
        Assert.assertThat(it.next(), is(2));
        Assert.assertThat(it.next(), is(3));
        Assert.assertNull(it.next());
        Assert.assertFalse(it.hasNext());
        it.next();
    }

}