package ru.job4j.collection.hashmap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<V> {

    private SimpleHashMap.Entry[] data = new SimpleHashMap.Entry[2];
    private int count = 0;
    private int modeCount = 0;

    public int getSize() {
        return data.length;
    }

    public int getCount() {
        return count;
    }

    public int getModeCount() {
        return modeCount;
    }

    private void checkLength() {
        int length = (int) Math.round(data.length * 0.75 - 1);
        if (count >= length) {
            int lengthNew = data.length * 2;
            SimpleHashMap.Entry[] temp = new SimpleHashMap.Entry[lengthNew];
            for (SimpleHashMap.Entry<K, V> elem : data) {
                if (elem != null) {
                    temp[hash(elem.getKey(), temp)] = elem;
                }
            }
            data = temp;
        }
    }
    
    private int hash(K key, SimpleHashMap.Entry[] array) {
        int h = key.hashCode();
        int shuffle = (key == null) ? 0 : h ^ (h >>> 16);
        int rsl =  (array.length - 1) & shuffle;
        return  rsl;
    }

    public boolean insert(K key, V value) {
        checkLength();
        int h = hash(key, data);
        if (data[h] == null) {
            data[h] = new SimpleHashMap.Entry<>(key, value);
            count++;
            modeCount++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        SimpleHashMap.Entry<K, V> elem = data[hash(key, data)];
        if (elem == null) {
            throw new NoSuchElementException();
        }
        return elem.getValue();
    }

    public boolean delete(K key) {
        int h = hash(key, data);
        if (data[h] != null && Objects.equals(key, data[h].getKey())) {
            data[h] = null;
            count--;
            modeCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new IteratorSimpleHashMap<K, V>(this, modeCount, data);
    }

     static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    
}
