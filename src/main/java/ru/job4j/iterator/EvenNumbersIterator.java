package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    int[] arr;
    int point = 0;
    public EvenNumbersIterator(int[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        for (; point < arr.length; point++) {
            if(arr[point] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arr[point++];
    }

}
