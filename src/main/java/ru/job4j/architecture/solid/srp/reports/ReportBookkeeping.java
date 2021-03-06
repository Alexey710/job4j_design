package ru.job4j.architecture.solid.srp.reports;

import java.util.function.Predicate;

public class ReportBookkeeping implements Report {

    private final Store store;

    public ReportBookkeeping(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(Math.round(employee.getSalary())).append(";");
        }
        return text.toString();
    }
}
