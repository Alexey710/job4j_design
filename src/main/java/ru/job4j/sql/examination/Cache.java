package ru.job4j.sql.examination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final  Map<String, SoftReference<String>> loadedFiles = new HashMap<>();
    private final Path path;

    public Cache(Path path) {
        this.path = path;
    }

    public String searchInCache(String name) throws IOException {
        Path pathLoad = Path.of(String.format("%s%s%s", path.toString(), "/", name));
        String strong = null;
        if (loadedFiles.containsKey(name) && loadedFiles.get(name) != null) {
            strong = loadedFiles.get(name).get();
            System.out.println("Вернул файл из кэша:");
        } else {
            loadToCache(pathLoad);
            strong = loadedFiles.get(name).get();
            System.out.println("Нашел файл в директории, и добавил в кэш:");
        }
        return strong;
    }

    private void loadToCache(Path path) throws IOException {
        String s = path.toFile().getName();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                StringBuilder sb = new StringBuilder();
                br.lines().forEach(sb :: append);
                SoftReference<String> soft = new SoftReference<>(sb.toString());
            loadedFiles.put(s, soft);
        }
    }

    public static void main(String[] args) throws IOException {
        Cache cache = new Cache(Path.of("C:/test/cache"));
        System.out.println(cache.searchInCache("Names.txt"));
        System.out.println(cache.searchInCache("Names.txt"));
        cache.loadedFiles.put("Names.txt", null);
        System.out.println("GC удалил SoftReference при переполнении оперативной памяти");
        System.out.println(cache.searchInCache("Names.txt"));
    }
}
