package ru.job4j.io.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Solder {
    private String rank;
    private int age;
    private Country country;
    private String[] rewards;

    public Solder() {
    }

    public Solder(String rank, int age, Country country, String[] rewards) {
        this.rank = rank;
        this.age = age;
        this.country = country;
        this.rewards = rewards;
    }

    public String getRank() {
        return rank;
    }

    public int getAge() {
        return age;
    }

    public Country getCountry() {
        return country;
    }

    public String[] getRewards() {
        return rewards;
    }

    @Override
    public String toString() {
        return "Solder{"
                + "rank='" + rank + '\''
                + ", age=" + age
                + ", country=" + country
                + ", rewards=" + Arrays.toString(rewards)
                + '}';
    }
}




