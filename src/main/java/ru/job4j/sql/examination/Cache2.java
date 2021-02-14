package ru.job4j.sql.examination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache2 {
    private final  Map<String, SoftReference<String>> loadedFiles = new HashMap<>();
    private final Path path;

    public Cache2(Path path) {
        this.path = path;
    }

    public String searchInCache(String name) throws IOException {
        String strong = null;
        Path pathLoad = Path.of(String.format("%s%s%s", path.toString(), "/", name));
        try {
        if (loadedFiles.containsKey(name)) {
            strong = loadedFiles.get(name).get();
            if (strong == null || strong.isEmpty()) {
                strong = createSoftReferenceAndPutToMap(name, pathLoad);
            }
        } else {
            loadedFiles.put(name, new SoftReference<>(""));
            strong = searchInCache(name);
        }
        } catch (NullPointerException e) {
                strong = createSoftReferenceAndPutToMap(name, pathLoad);
        }
        return strong;
    }

    private String getStringFromFile(Path path) throws IOException {
        String s = path.toFile().getName();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                StringBuilder sb = new StringBuilder();
                br.lines().forEach(sb :: append);
            return sb.toString();
        }
    }

    private String createSoftReferenceAndPutToMap(String name, Path pathLoad) throws IOException {
        String strong = getStringFromFile(pathLoad);
        SoftReference<String> soft = new SoftReference<>(strong);
        loadedFiles.put(name, soft);
        return strong;
    }

    public static void main(String[] args) throws IOException {
        Cache2 cache = new Cache2(Path.of("C:/test/cache"));
        System.out.println(cache.searchInCache("Names.txt"));
        System.out.println(cache.searchInCache("Names.txt"));
        /*"GC удалил String в SoftReference"*/
        cache.loadedFiles.put("Names.txt", new SoftReference<>(null));
        System.out.println(cache.searchInCache("Names.txt"));
        /*"GC удалил SoftReference"*/
        cache.loadedFiles.put("Names.txt", null);
        System.out.println(cache.searchInCache("Names.txt"));
    }
}
