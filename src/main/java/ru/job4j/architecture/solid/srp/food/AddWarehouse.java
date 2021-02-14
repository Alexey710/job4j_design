package ru.job4j.architecture.solid.srp.food;

public class AddWarehouse implements Strategy {

    @Override
    public void doOperation(Food food, Store store) {
        store.add(food);
    }
}
