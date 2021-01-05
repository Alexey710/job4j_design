package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteFirstLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        DeleteFirstLinked<Integer> linked = new DeleteFirstLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        DeleteFirstLinked<Integer> linked = new DeleteFirstLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        DeleteFirstLinked<Integer> linked = new DeleteFirstLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }
}