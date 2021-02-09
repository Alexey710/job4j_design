package ru.job4j.gc.examination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final  Map<String, SoftReference<String>> foundedFiles = new HashMap<>();
    private final Path path;

    public Cache(Path path) {
        this.path = path;
    }

    public Map<String, SoftReference<String>> getFoundedFiles() {
        return foundedFiles;
    }

    public SoftReference<String> searchInCache(String name) throws IOException {
        SoftReference<String> rsl = null;
        SoftReference<String> found;
        if (foundedFiles.containsKey(name) && (found = foundedFiles.get(name)) != null) {
            System.out.println("Вернул файл из кэша:");
            return found;
        } else {
            Path pathLoad = Path.of(String.format("%s%s%s", path.toString(), "/", name));
            loadToCache(pathLoad);
        }
        if (foundedFiles.containsKey(name)) {
            System.out.println("Нашел файл в директории, и добавил в кэш:");
            rsl = foundedFiles.get(name);
        }
        return rsl;
    }

    private void loadToCache(Path path) throws IOException {
        String s = path.toFile().getName();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                StringBuilder sb = new StringBuilder();
                br.lines().forEach(sb :: append);
                SoftReference<String> soft = new SoftReference<>(sb.toString());
                foundedFiles.put(s, soft);
        }
    }

    public static void main(String[] args) throws IOException {
        Cache cache = new Cache(Path.of("C:/test/cache"));
        System.out.println(cache.searchInCache("Names.txt").get());
        System.out.println(cache.searchInCache("Names.txt").get());
        cache.getFoundedFiles().put("Names.txt", null);
        System.out.println("GC удалил SoftReference при переполнении оперативной памяти");
        System.out.println(cache.searchInCache("Names.txt").get());
    }
}
