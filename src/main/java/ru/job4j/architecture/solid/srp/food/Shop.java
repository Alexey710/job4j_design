package ru.job4j.architecture.solid.srp.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
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
        int discount = 40;
        if (condition > 75 && condition < 100) {
            food.setDiscount(discount);
        }
        return condition >= 25 && condition < 100;
    }

    @Override
    public Strategy getStrategy() {
        return new AddShop();
    }
}
