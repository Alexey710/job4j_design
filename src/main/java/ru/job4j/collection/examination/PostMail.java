package ru.job4j.collection.examination;

import java.util.*;

public class PostMail {

    Map<String, Set<String>> combine(Map<String, Set<String>> current) {
        Map<String, String> library = new HashMap<>();
        Map<String, Set<String>> users = current;
        LinkedList<Pair> pairs = new LinkedList<>();
        for (Map.Entry<String, Set<String>> mapElem : current.entrySet()) {
            String key = mapElem.getKey();
            Set<String> values = mapElem.getValue();
            for (String mail : values) {
                if (library.containsKey(mail)) {
                    pairs.add(new Pair(key, library.get(mail)));
                }
                library.put(mail, key);
            }
        }
        for (Pair pair : pairs) {
            Set<String> one = current.get(pair.getFirst());
            Set<String> two = current.get(pair.getSecond());
            one.addAll(two);
            two.clear();
        }

        Iterator<Map.Entry<String, Set<String>>> it = users.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().size() == 0) {
                it.remove();
            }
        }

        if (pairs.size() != 0) {
            library.clear();
            pairs.clear();
            combine(current);
        }
        return users;
    }
}

class Pair {
    private String first;
    private String second;

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
