package ru.job4j.architecture.solid.srp.food;

import java.util.List;

public interface Store {

    void add(Food food);

    boolean accept(Food food);

    Strategy getStrategy();

    List<Food> getList();
}
