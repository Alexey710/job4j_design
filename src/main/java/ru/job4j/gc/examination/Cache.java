package ru.job4j.gc.examination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final  Map<String, String> foundedFiles = new HashMap<>();
    private final Path path;

    public Cache(Path path) {
        this.path = path;
    }

    public Map<String, String> getFoundedFiles() {
        return foundedFiles;
    }

    public String search(String name) throws IOException {
        String rsl = "";
        if (foundedFiles.containsKey(name)) {
            System.out.println("Вернул файл из кэша:");
            return foundedFiles.get(name);
        } else {
            Files.walkFileTree(path, new VisitResult(name, p -> p.equals(name), this));
        }
        if (foundedFiles.containsKey(name)) {
            System.out.println("Нашел файл в директории, и добавил в кэш:");
            rsl = foundedFiles.get(name);
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        Cache cache = new Cache(Path.of("C:/test/cache"));
        System.out.println(cache.search("Names.txt"));
        System.out.println(cache.search("Names.txt"));
    }
}
