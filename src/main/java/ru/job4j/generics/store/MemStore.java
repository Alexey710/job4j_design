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
        int index = getIndex(id);
        if (index > 0) {
            mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = getIndex(id);
        if (index > 0) {
            mem.remove(index);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        int rsl = getIndex(id);
        return rsl > 0 ? mem.get(rsl) : null;
    }

    public int getIndex(String id) {
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                return mem.indexOf(elem);
            }
        }
        return -1;
    }
}