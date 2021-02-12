package ru.job4j.architecture.srp;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}
