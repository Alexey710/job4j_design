package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[1];
    private int modCount = 0;
    private int expectedModCount;

    public T get(int index) {
        return (T) container[Objects.checkIndex(index, modCount)];
    }

    public void add(T model) {
        if (modCount == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[modCount] = model;
        modCount++;
    }

    public Object[] getContainer() {
        return container;
    }

    public int getModCount() {
        return modCount;
    }

    public int getExpectedModCount() {
        return expectedModCount;
    }

    @Override
    public Iterator<T> iterator() {
        expectedModCount = modCount;
        return (Iterator<T>) new IteratorSimpleArray(this);
    }
}
