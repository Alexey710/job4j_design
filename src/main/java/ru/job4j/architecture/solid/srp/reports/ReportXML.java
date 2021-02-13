package ru.job4j.architecture.solid.srp.reports;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    public static String toXmlFormat(Employee worker) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(worker, writer);
            xml = writer.getBuffer().toString();
        }
        return xml;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        for (Employee employee : store.findBy(filter)) {
            try {
                text.append(toXmlFormat(employee));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text.toString();
    }

}
