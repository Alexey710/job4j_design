package ru.job4j.io.examination;

import java.util.HashMap;
import java.util.Map;
/* java -jar find.jar -d c:/ -n *.txt -m -o log.txt */

public class ArgsValidation {
    private Map<String, String> values = new HashMap<>();

    public ArgsValidation(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            String next = args[i + 1];
            if (args[i].equals("-jar") && next.charAt(0) != '-') {
                values.put(args[i], next);
            }
            if (args[i].equals("-d") && next.charAt(0) != '-') {
                values.put(args[i], next);
            }
            if (args[i].equals("-n") && next.charAt(0) != '-') {
                values.put(args[i], next);
            }
            if (args[i].equals("-m")) {
                values.put(args[i], args[i]);
            }
            if (args[i].equals("-f")) {
                values.put(args[i], args[i]);
            }
            if (args[i].equals("-r")) {
                values.put(args[i], args[i]);
            }
            if (args[i].equals("-o") && next.charAt(0) != '-') {
                values.put(args[i], next);
            }
        }
    }

    public Map<String, String> getValues() {
        return values;
    }

    public String getJar() {
        if (!values.containsKey("-jar")) {
            throw new IllegalArgumentException(
                    "Parameter \"-jar\" is absent.");
        }
        return values.get("-jar");
    }

    public String getDirectory() {
        if (!values.containsKey("-d")) {
            throw new IllegalArgumentException(
                    "Parameter \"-d\" is absent.");
        }
        return values.get("-d");
    }

    public String getN() {
        if (!values.containsKey("-n")) {
            throw new IllegalArgumentException(
                    "Parameter \"-n\" is absent.");
        }
        return values.get("-n");
    }

    public String getRegExp() {
        if (!values.containsKey("-r")) {
            throw new IllegalArgumentException(
                    "Parameter \"-n\" is absent.");
        }
        return values.get("-n");
    }

    public String getMask() {
        if (!values.containsKey("-m")) {
            throw new IllegalArgumentException(
                    "Parameter \"-n\" is absent.");
        }
        return values.get("-n");
    }

    public String getFullNameFile() {
        if (!values.containsKey("-f")) {
            throw new IllegalArgumentException(
                    "Parameter \"-n\" is absent.");
        }
        return values.get("-n");
    }

    public String getLog() {
        if (!values.containsKey("-o")) {
            throw new IllegalArgumentException(
                    "Parameter \"-o\" is absent.");
        }
        return values.get("-o");
    }

}
