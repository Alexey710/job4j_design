package ru.job4j.architecture.solid.isp.violation;

public interface Aggregator {
    void aggregateAll();

    void aggregateOneType();
}
