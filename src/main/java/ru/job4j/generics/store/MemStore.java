package ru.job4j.generics.store;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                mem.set(getIndex(elem), model);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                mem.remove(getIndex(elem));
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                return elem;
            }
        }
        return null;
    }

    public int getIndex (T elem) {
        return mem.indexOf(elem);
    }
}