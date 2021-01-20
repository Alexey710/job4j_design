package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles extends SimpleFileVisitor<Path> {
    private String ext;
    private Predicate<String> predicate;

    public PrintFiles(String ext, Predicate<String> predicate) {
        this.ext = ext;
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String s = file.toFile().getName();
        if (predicate.test(s)) {
            Search.getFoundItems().add(file.toString());
        }
        return CONTINUE;
    }
}