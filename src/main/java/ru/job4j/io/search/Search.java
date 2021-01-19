package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private static List<Path> foundItems = new ArrayList<>();

    public static List<Path> getFoundItems() {
        return foundItems;
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        foundItems.clear();
        Files.walkFileTree(root, new PrintFiles(ext, p -> p.equals(ext)));
        return foundItems;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        Path start = Path.of(args[0]);
        String extension = args[1];
        System.out.println(search(start, extension));
    }
}