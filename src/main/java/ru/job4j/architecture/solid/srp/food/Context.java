package ru.job4j.architecture.solid.srp.food;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Food food, Store store) {
        strategy.doOperation(food, store);
    }

}