package ru.job4j.io.xml;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Country {
    @javax.xml.bind.annotation.XmlElement(name = "name")
    private String country;
    @javax.xml.bind.annotation.XmlElement(name = "code")
    private int countryCode;

    public Country() {
    }

    public Country(String country, int countryCode) {
        this.country = country;
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public int getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return "Country{"
                + "country='" + country + '\''
                + ", countryCode=" + countryCode
                + '}';
    }
}