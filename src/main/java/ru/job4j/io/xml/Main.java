package ru.job4j.io.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Solder solder = new Solder("solder", 30,
                new Country("Russia", 4600000), new Rewards(new String[]{"medal1", "medal2"}));
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Solder.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(solder, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для сериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Solder result = (Solder) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        //marshaller.marshal(book, new File("./book.xml"));
    }
}
