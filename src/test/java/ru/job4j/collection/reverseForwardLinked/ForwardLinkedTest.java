package ru.job4j.collection.reverseForwardLinked;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRevertOnlyOneElement() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.revert();
    }
}