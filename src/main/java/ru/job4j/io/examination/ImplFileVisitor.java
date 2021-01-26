package ru.job4j.io.examination;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

public class ImplFileVisitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> predicate;
    private final SearchFile searchFile;

    public ImplFileVisitor(Predicate<Path> predicate, SearchFile searchFile) {
        this.predicate = predicate;
        this.searchFile = searchFile;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       if (predicate.test(file)) {
            String s = file.toFile().getName();
            searchFile.getResult().add(s);
        }
        return super.visitFile(file, attrs);
    }
}

