package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private static List<Path> pathArrayList = new ArrayList<>();

    public static void addPath(Path current) {
        pathArrayList.add(current);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        List<Path> paths = pathArrayList;
        Files.walkFileTree(root, new PrintFiles(ext));
        return paths;
    }

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:\\");
        search(start, "js");
    }
}