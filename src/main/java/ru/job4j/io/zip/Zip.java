package ru.job4j.io.zip;

import ru.job4j.io.search.PrintFiles;
import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(Set<String> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(
                             new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String path : sources) {
                zip.putNextEntry(new ZipEntry(path));
                System.out.println("записан файл " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(
                             new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> search(Path root, String ext) throws IOException {
        Search.getFoundItems().clear();
        Files.walkFileTree(root, new PrintFiles(ext, p -> {
            return !p.endsWith(ext);
        }
        ));
        return Search.getFoundItems();
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        argZip.valid();
        Path root = Path.of(argZip.directory());
        String ext = argZip.exclude();
        Zip zip = new Zip();
        Set<String> sources = zip.search(root, ext);
        File target = Path.of(argZip.output()).toFile();
        zip.packFiles(sources, target);
    }
}
