package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorSimpleArray implements Iterator {
    private SimpleArray simpleArray;
    private Object[] container;
    private int expectedModCount;
    private int point = 0;

    public IteratorSimpleArray(SimpleArray simpleArray) {
        this.container = simpleArray.getContainer();
        this.expectedModCount = simpleArray.getExpectedModCount();
        this.simpleArray = simpleArray;
    }

    @Override
    public boolean hasNext() {
        if (expectedModCount != simpleArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return point < expectedModCount;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return container[point++];
    }
}
