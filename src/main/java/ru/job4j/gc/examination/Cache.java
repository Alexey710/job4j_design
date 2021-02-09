package ru.job4j.gc.examination;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
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

    public SoftReference<String> search(String name) throws IOException {
        SoftReference<String> rsl = null;
        if (foundedFiles.containsKey(name)) {
            SoftReference<String> found;
            if ((found = foundedFiles.get(name)) == null) {
                return new SoftReference<String>("SoftReference is deleted of GC");
            }
            System.out.println("Вернул файл из кэша:");
            return found;

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
        System.out.println(cache.search("Names.txt").get());
        System.out.println(cache.search("Names.txt").get());
        /*GC удаляет SoftReference при переполнении оперативной памяти*/
        cache.getFoundedFiles().put("Names.txt", null);
        System.out.println(cache.search("Names.txt").get());
    }
}
