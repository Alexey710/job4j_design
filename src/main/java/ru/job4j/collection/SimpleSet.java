package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public SimpleArray<T> getSimpleArray() {
        return simpleArray;
    }

    private boolean checkForAdd(T e) {
        for (Object elem : simpleArray.getContainer()) {
            return Objects.equals(elem, e);
        }
        return false;
    }

    void add(T e) {
        if (!checkForAdd(e)) {
            simpleArray.add(e);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

}
