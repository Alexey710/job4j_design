package ru.job4j.io.gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON {

    public static void main(String[] args) {
        final String solderJson = "{" + "rank" + ":" + "marine" + ","
                + "age" + ":" + "30" + "," + "country" + ":"
                + "{" + "country" + ":" + "Russia" + ","
                + "countryCode" + ":" + "460000" + "}" + ","
                + "rewards" + ":" + "[" + "Medal" + "," + "Award" + "]" + "}";
        JSONObject jsonSolder1 = new JSONObject(solderJson);
        System.out.println(jsonSolder1);
        JSONArray jsonRewards = new JSONArray(new String[] {"Medal", "Award"});
        JSONObject jsonSolder2 = new JSONObject(solderJson);
        jsonSolder2.put("rank", "marine");
        jsonSolder2.put("age", 30);
        jsonSolder2.put("country", new Country("Russia", 460000));
        jsonSolder2.put("rewards", jsonRewards);
        System.out.println(jsonSolder2);
        Solder solder = new Solder(
                "marine", 30,
                new Country("Russia", 460000),
                new String[]{"Medal", "Award"});
        System.out.println(new JSONObject(solder).toString());

    }
}
