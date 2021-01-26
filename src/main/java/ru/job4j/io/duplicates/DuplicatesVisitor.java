package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String name = file.toFile().getName();
        Long size = file.toFile().getTotalSpace();
        FileProperty entry = new FileProperty(size, name, file);
        if (files.contains(entry)) {
            String rsl = String.format(
                    "Found duplicated file. Path = %s", file.toAbsolutePath());
            System.out.println(rsl);
            System.out.println("________________________________");
        } else {
            files.add(entry);
        }

        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc instanceof AccessDeniedException) {
            return FileVisitResult.SKIP_SUBTREE;
        }

        return super.visitFileFailed(file, exc);
    }
}