package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    private Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        array =  new Object[size];
    }

    void add(T model) {
        array[Objects.checkIndex(index++, array.length)] = model;
    }

    void set(int indexInsert, T model) {
        if (indexInsert >= 0 && indexInsert < index) {
            array[indexInsert] = model;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, index));
        }
    }

    T get(int indexSearch) {
        return (T) array[Objects.checkIndex(indexSearch, array.length + 1)];
    }

    void remove(int indexDelete) {
        if (indexDelete >= 0 && indexDelete < index) {
            Object[] result =  new Object[array.length - 1];
            System.arraycopy(array, 0, result, 0, indexDelete);
            System.arraycopy(array, indexDelete + 1,
                    result, indexDelete, array.length - indexDelete - 1);
            array = result;
            index--;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, index));
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator it = new IteratorForSimpleArray(array);
        return it;
    }

}
