package ru.job4j.io.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Solder {
    private  String rank;
    private  int age;
    private  Country country;
    private  String[] rewards;

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

    public static void main(String[] args) {
        Country country = new Country("Russia", 460000);
        final Solder solder = new Solder(
                "marine", 30, country, new String[] {"Medal", "Award"});

        /* Преобразуем объект solder в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(solder));

        /* Преобразуем json-строку в объект*/
        final String solderJson = "{" + "rank" + ":" + "marine" + ","
                + "age" + ":" + "30" + "," + "country" + ":"
                + "{" + "country" + ":" + "Russia" + ","
                + "countryCode" + ":" + "460000" + "}" + ","
                + "rewards" + ":" + "[" + "Medal" + "," + "Award" + "]" + "}";
        final Solder solderMod = gson.fromJson(solderJson, Solder.class);
        System.out.println(solderMod);
    }
}




