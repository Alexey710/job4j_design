package ru.job4j.io.examination;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class SearchFile extends SimpleFileVisitor<Path> {
    private List<String> result = new LinkedList<>();
    private final ArgsValidation argsValidation;

    public SearchFile(String[] args) {
        argsValidation = new ArgsValidation(args);
    }

    private void writeLog() throws IOException {
        if (argsValidation.getValues().containsKey("-r")) {
            String regExp = argsValidation.getN();
            searchRegExp("glob:**" + regExp);
        } else if (argsValidation.getValues().containsKey("-f")) {
            String fullName = argsValidation.getN();
            searchFullName(fullName);
        } else if (argsValidation.getValues().containsKey("-m")) {
            searchMask();
        }
        try (PrintWriter printWriter = new PrintWriter(argsValidation.getLog())) {
            for (String entry: result) {
                printWriter.println(entry);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** for test RegExp = "glob:**.js" or "**.{js,txt}" */

    private void searchRegExp(String regExp) throws IOException {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(regExp);
        Path directory = Path.of(argsValidation.getDirectory());
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(file)) {
                    String s = file.toFile().getName();
                    result.add(s);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void searchFullName(String fullName) throws IOException {
        String target = Path.of(fullName).toFile().getName();
        Path directory = Path.of(argsValidation.getDirectory());
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                String current = file.toFile().getName();
                if (current.equals(target)) {
                    result.add(current);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void searchMask() throws IOException {
        String s = argsValidation.getMask();
        String[] arr = s.split("\\*");
        String ext = arr[1];
        Path directory = Path.of(argsValidation.getDirectory());
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                String current = file.toFile().getName();
                if (current.endsWith(ext)) {
                    result.add(current);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void main(String[] args) throws IOException {
        new SearchFile(args).writeLog();
    }
}
