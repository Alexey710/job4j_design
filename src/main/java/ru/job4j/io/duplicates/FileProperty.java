package ru.job4j.io.duplicates;

import java.nio.file.Path;
import java.util.Objects;

public class FileProperty {

    private long size;

    private String name;

    private Path path;

    public FileProperty(long size, String name, Path path) {
        this.size = size;
        this.name = name;
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        boolean eql = size == that.size && Objects.equals(name, that.name);
        if (eql) {
            String rsl = String.format(
                    "Found duplicated file. Path = %s", that.path.toAbsolutePath());
            System.out.println(rsl);
        }
        return eql;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}