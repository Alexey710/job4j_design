package ru.job4j.collection.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForLinkedList<E> implements Iterator {
    private SimpleLinkedList<E> simpleLinkedList;
    private int point = 0;
    private final int expectedMod;

    IteratorForLinkedList(SimpleLinkedList<E> simpleLinkedList, int modCount) {
        this.simpleLinkedList = simpleLinkedList;
        expectedMod = modCount;
    }

    @Override
    public boolean hasNext() {
        if (simpleLinkedList.getModCount() != expectedMod) {
            throw new ConcurrentModificationException();
        }
        return point < expectedMod;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return simpleLinkedList.get(point++);
    }

}
