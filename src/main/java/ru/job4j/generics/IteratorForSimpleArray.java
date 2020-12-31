package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForSimpleArray implements Iterator<Object> {
    private final Object[] data;
    private int point;

    public IteratorForSimpleArray(Object[] data) {
        this.data = data;
        point = 0;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

}
