package ru.job4j.architecture.solid.srp.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public AddWarehouse getStrategy() {
        return new AddWarehouse();
    }

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return food.getPercentOfSpentDays() < 25;
    }

    @Override
    public String toString() {
        return "Warehouse{"
                + "list=" + list
                + '}';
    }
}
