package ru.job4j.architecture.solid.srp.reports;

import org.json.JSONObject;
import org.json.XML;

import java.util.function.Predicate;

import static ru.job4j.architecture.solid.srp.reports.ReportXML.toXmlFormat;

public class ReportJSON implements Report {
    public static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        for (Employee employee : store.findBy(filter)) {
            try {
                JSONObject xml = XML.toJSONObject(toXmlFormat(employee));
                String json = xml.toString(PRETTY_PRINT_INDENT_FACTOR);
                text.append(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    }
}
