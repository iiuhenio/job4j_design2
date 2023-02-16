package ru.job4j.io.duplicates;

import java.util.Objects;

/**
 * Для решения данной задачи нам нужно выделить модель данных файла,
 * которая описывается двумя свойствами: размером и именем.
 * Реализуем ее
 */

public class FileProperty {

    private long size;

    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileProperty)) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return getSize() == that.getSize() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize(), getName());
    }
}