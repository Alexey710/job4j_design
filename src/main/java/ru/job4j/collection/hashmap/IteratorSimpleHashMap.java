package ru.job4j.collection.hashmap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorSimpleHashMap<K, V> implements Iterator<V> {
    private SimpleHashMap<K, V> simpleHashMap;
    private int pointer = 0;
    private int expectedModeCount;
    SimpleHashMap.Entry[] data;

    public IteratorSimpleHashMap(SimpleHashMap<K, V> simpleHashMap, int modeCount, SimpleHashMap.Entry[] data) {
        this.simpleHashMap = simpleHashMap;
        expectedModeCount = modeCount;
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (expectedModeCount != simpleHashMap.getModeCount()) {
            throw new ConcurrentModificationException();
        }
        return pointer < simpleHashMap.getSize() && simpleHashMap.getCount() != 0;
    }

    @Override
    public V next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = pointer; i < simpleHashMap.getSize(); i++) {
            SimpleHashMap.Entry<K, V> elem = data[i];
            pointer++;
            if (elem != null) {
                V rsl = elem.getValue();
                return rsl;
            }
        }
        return null;
    }
}
