package ru.job4j.gc.examination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class VisitResult extends SimpleFileVisitor<Path> {
    private Cache cache;
    private String name;
    private Predicate<String> predicate;

    public VisitResult(String name, Predicate<String> predicate, Cache cache) {
        this.name = name;
        this.predicate = predicate;
        this.cache = cache;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        String s = path.toFile().getName();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            if (predicate.test(s)) {
                StringBuilder sb = new StringBuilder();
                br.lines().forEach(sb :: append);
                SoftReference<String> soft = new SoftReference<>(sb.toString());
                cache.getFoundedFiles()
                        .put(s, soft);
            }
        }
        return CONTINUE;
    }
}