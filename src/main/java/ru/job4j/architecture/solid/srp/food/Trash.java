package ru.job4j.architecture.solid.srp.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> list = new ArrayList<>();

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
        long condition = food.getPercentOfSpentDays();
        return condition >= 100;
    }

    @Override
    public Strategy getStrategy() {
        return new AddTrash();
    }
}
