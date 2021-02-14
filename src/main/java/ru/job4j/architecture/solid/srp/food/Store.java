package ru.job4j.architecture.solid.srp.food;

import java.util.List;

public interface Store {

    void add(Food food);

    List<Food> getList();
}
