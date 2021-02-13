package ru.job4j.architecture.solid.srp.reports;

import java.util.function.Predicate;

public class ReportHtml implements Report {

    private final Store store;

    public ReportHtml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append("<body>");
        text.append("<div>");
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        text.append("</div>");
        text.append("</body>");
        text.append("</html>");
        return text.toString();
    }
}
