package ru.job4j.io.zip;

import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(Set<String> sources, File target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(
                             new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String path : sources) {
                if (!Path.of(path).toFile().isDirectory()) {
                    zip.putNextEntry(new ZipEntry(path));
                    System.out.println("записан файл " + path);
                } else {
                    zip.putNextEntry(new ZipEntry(path));
                    System.out.println("записана папка " + path);
                    Set<String> subFolder = Search.search(Path.of(path), "");
                    packFiles(subFolder, target);
                }
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

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        argZip.valid();
        Path root = Path.of(argZip.directory());
        Set<String> sources = Search.search(root, "");
        File target = Path.of(argZip.output()).toFile();
        new Zip().packFiles(sources, target);
    }
}
