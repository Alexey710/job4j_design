package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles implements FileVisitor<Path> {
    private String ext;
    private Predicate<String> predicate;

    public PrintFiles(String ext, Predicate<String> predicate) {
        this.ext = ext;
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        int length = ext.length();
        Path path = file.toAbsolutePath();
        String s = path.toFile().getName();
        String substring = s.substring(s.length() - length);
        if (predicate.test(substring)) {
            Search.getFoundItems().add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir,
                                             BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                              IOException exc) throws IOException {
        return CONTINUE;
    }
}