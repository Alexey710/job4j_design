package ru.job4j.architecture.solid.srp.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportHr implements Report {
    private List<Employee> employees = new ArrayList<>();
    private final MemStoreHr store = new MemStoreHr();

    public ReportHr(List<Employee> employees) {
        this.employees = employees;
        for (Employee elem : employees) {
            store.add(elem);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}
