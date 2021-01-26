package ru.job4j.io.xml;

public class Country {
    private String country;
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