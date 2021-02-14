package ru.job4j.architecture.solid.srp.food;

public class AddTrash implements Strategy {

    @Override
    public void doOperation(Food food, Store store) {
        store.add(food);
    }
}