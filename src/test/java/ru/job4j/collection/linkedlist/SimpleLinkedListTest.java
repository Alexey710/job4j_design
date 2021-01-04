package ru.job4j.collection.linkedlist;

import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.CoreMatchers.is;

public class SimpleLinkedListTest {

    @Test
    public void whenLinkLastAndGet() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.linkLast("item1");
        linkedList.linkLast("item2");
        linkedList.linkLast("item3");
        linkedList.linkLast("item4");
        Assert.assertThat(linkedList.get(0), is("item1"));
        Assert.assertThat(linkedList.get(3), is("item4"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvalidGet() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.linkLast("item1");
        linkedList.linkLast("item2");
        linkedList.linkLast("item3");
        linkedList.linkLast("item4");
        linkedList.get(4);
    }

    @Test
    public void whenIteratorNext() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.linkLast("item1");
        linkedList.linkLast("item2");
        linkedList.linkLast("item3");
        linkedList.linkLast("item4");
        Iterator it = linkedList.iterator();
        Assert.assertThat(it.next(), is("item1"));
        Assert.assertThat(it.next(), is("item2"));
        Assert.assertThat(it.next(), is("item3"));
        Assert.assertThat(it.next(), is("item4"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorChangeBeforeNext() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.linkLast("item1");
        linkedList.linkLast("item2");
        Iterator it = linkedList.iterator();
        linkedList.linkLast("item3");
        linkedList.linkLast("item4");

        Assert.assertThat(it.next(), is("item1"));
        Assert.assertThat(it.next(), is("item2"));
        Assert.assertThat(it.next(), is("item3"));
        Assert.assertThat(it.next(), is("item4"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextNoSuchElement() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.linkLast("item1");
        linkedList.linkLast("item2");
        Iterator it = linkedList.iterator();
        Assert.assertThat(it.next(), is("item1"));
        Assert.assertThat(it.next(), is("item2"));
        it.next();
    }
}