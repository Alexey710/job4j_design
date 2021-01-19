package ru.job4j.io.zip;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {
    private final Map<String, String> values = new HashMap<>();
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Not enough arguments for creation of ZIP archive");
        }
        for (String s : args) {
            if (s.contains("=")) {
                String[] arr = s.substring(1).split("=");
                values.put(arr[0], arr[1]);
            }
        }
        return true;
    }

    public String directory() {
        if (!values.containsKey("d")) {
            throw new IllegalArgumentException(
                    "Parameter \"-d\" is absent.");
        }
        return values.get("d");
    }

    public String exclude() {
        if (!values.containsKey("e")) {
            throw new IllegalArgumentException(
                    "Parameter \"-e\" is absent.");
        }
        return values.get("e");
    }

    public String output() {
        if (!values.containsKey("o")) {
            throw new IllegalArgumentException(
                    "Parameter \"-o\" is absent.");
        }
        return values.get("o");
    }
}
