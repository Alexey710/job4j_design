package ru.job4j.io.examination;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

public class SearchFile extends SimpleFileVisitor<Path> {
    private List<String> result = new LinkedList<>();
    private final ArgsValidation argsValidation;

    public SearchFile(String[] args) {
        argsValidation = new ArgsValidation(args);
    }

    public List<String> getResult() {
        return result;
    }

    private void writeLog() throws IOException {
        if (argsValidation.getValues().containsKey("-r")) {
            String regExp = "glob:**" + argsValidation.getN();
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher(regExp);
            Path directory = Path.of(argsValidation.getDirectory());
            Files.walkFileTree(directory, new ImplFileVisitor(p -> matcher.matches(p), this));
        } else if (argsValidation.getValues().containsKey("-f")) {
            String fullName = argsValidation.getN();
            String target = Path.of(fullName).toFile().getName();
            Path directory = Path.of(argsValidation.getDirectory());
            Files.walkFileTree(directory,
                    new ImplFileVisitor(p -> target.equals(p.toFile().getName()), this));
        } else if (argsValidation.getValues().containsKey("-m")) {
            String s = argsValidation.getMask();
            String[] arr = s.split("\\*");
            String ext = arr[1];
            Path directory = Path.of(argsValidation.getDirectory());
            Files.walkFileTree(
                    directory, new ImplFileVisitor(p -> p.toString().endsWith(ext), this));
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

    public static void main(String[] args) throws IOException {
        new SearchFile(args).writeLog();
    }
}
