package ru.job4j.collection.examination;

import java.util.*;

public class Analize {

    private final Map<User, User> currentMap = new HashMap<>();

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int added = 0;
        int deleted = 0;
        for (User elem : current) {
            currentMap.put(elem, elem);
        }
        for (User elem : previous) {
            Optional<User> optionalUser = Optional.ofNullable(currentMap.get(elem));
            if (optionalUser.isPresent()) {
                if (Objects.equals(elem, optionalUser.get())) {
                        if (!Objects.equals(elem.getName(), currentMap.get(elem).getName())) {
                            changed++;
                    }
                }
            } else {
                deleted++;
            }
            added = currentMap.size() + deleted - previous.size();
        }
        return new Info(added, changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }

    public static void main(String[] args) {
        Analize.User user0 = new Analize.User(0, "Ivan");
        Analize.User user1 = new Analize.User(1, "Stepan");
        Analize.User user2 = new Analize.User(2, "Evgeniy");
    }

}