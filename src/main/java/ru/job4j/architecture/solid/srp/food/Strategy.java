package ru.job4j.architecture.solid.srp.food;

public interface Strategy {
    public void doOperation(Food food, Store store);
}
