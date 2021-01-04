package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorSimpleArray implements Iterator {
    private SimpleArray simpleArray;
    private Object[] container;
    private int expectedModCount;
    private int point = 0;

    public IteratorSimpleArray(SimpleArray simpleArray, int modCount) {
        this.container = simpleArray.getContainer();
        this.expectedModCount = modCount;
        this.simpleArray = simpleArray;
    }

    @Override
    public boolean hasNext() {
        if (expectedModCount != simpleArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return point < simpleArray.getSize();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return container[point++];
    }
}
