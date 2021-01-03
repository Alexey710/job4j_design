package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForSimpleArray implements Iterator<Object> {
    private SimpleArray simpleArray;
    private final Object[] data;
    private int point;

    public IteratorForSimpleArray(SimpleArray simpleArray) {
        this.simpleArray = simpleArray;
        this.data = simpleArray.getArray();
        point = 0;
    }

    @Override
    public boolean hasNext() {
        return point < simpleArray.getIndex();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

}
