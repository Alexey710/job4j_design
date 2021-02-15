package ru.job4j.architecture.solid.isp.menu;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(ItemMenu item, String name) {
        strategy.doOperation(item, name);
    }

}