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
        array[Objects.checkIndex(index++, array.length + 1)] = model;
    }

    void set(int indexInsert, T model) {
        if (indexInsert >= 0 && indexInsert < array.length) {
            array[indexInsert] = model;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, array.length));
        }
    }

    T get(int indexSearch) {
        return (T) array[Objects.checkIndex(indexSearch, array.length + 1)];
    }

    void remove(int indexDelete) {
        if (indexDelete >= 0 && indexDelete < array.length) {
            Object[] result =  new Object[array.length - 1];
            System.arraycopy(array, 0, result, 0, indexDelete);
            System.arraycopy(array, indexDelete + 1,
                    result, indexDelete, array.length - indexDelete - 1);
            array = result;
        } else {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, array.length));
        }

    }

    @Override
    public Iterator<T> iterator() {
        Iterator it = new IteratorForSimpleArray(array);
        return it;
    }

}
