package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    private Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        array =  new Object[size];
    }

    public Object[] getArray() {
        return array;
    }

    public int getIndex() {
        return index;
    }

    void add(T model) {
        array[Objects.checkIndex(index++, array.length)] = model;
    }

    void set(int indexInsert, T model) {
        if (indexInsert < 0 || indexInsert >= index) {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, index - 1));
        }
        array[indexInsert] = model;
    }

    T get(int indexSearch) {
        if (indexSearch < 0 || indexSearch >= index) {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, index - 1));
        }
        return (T) array[Objects.checkIndex(indexSearch, index)];
    }

    void remove(int indexDelete) {
        if (indexDelete < 0 || indexDelete >= index) {
            throw new IndexOutOfBoundsException(
                    String.format("Allowed index from %s to %s", 0, index - 1));
        }
        System.arraycopy(array, indexDelete + 1,
                array, indexDelete, array.length - indexDelete - 1);
        index--;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator it = new IteratorForSimpleArray(this);
        return it;
    }

}
