package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public SimpleArray<T> getSimpleArray() {
        return simpleArray;
    }

    void add(T e) {
        if (simpleArray.getSize() == 0) {
            simpleArray.add(e);
            return;
        }
        for (Object elem : simpleArray.getContainer()) {
            if (!elem.equals(e)) {
                simpleArray.add(e);
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

}
