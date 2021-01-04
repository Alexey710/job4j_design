package ru.job4j.collection.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForLinkedList<E> implements Iterator {
    private SimpleLinkedList<E> simpleLinkedList;
    private int point = 0;
    private final int expectedMod;
    private SimpleLinkedList.Node current = new SimpleLinkedList.Node(null, null, null);

    IteratorForLinkedList(SimpleLinkedList<E> simpleLinkedList, int modCount) {
        this.simpleLinkedList = simpleLinkedList;
        expectedMod = modCount;
    }

    @Override
    public boolean hasNext() {
        if (simpleLinkedList.getModCount() != expectedMod) {
            throw new ConcurrentModificationException();
        }
        return point < simpleLinkedList.getSize();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (current.getPrev() == null && current.getNext() == null) {
            current = simpleLinkedList.getFirst();
        } else {
            current = current.getNext();
        }
        point++;
        return current.getItem();
    }

}
