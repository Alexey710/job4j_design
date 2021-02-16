package ru.job4j.architecture.solid.isp.menu;

public interface Menu {
    void addItem(ItemMenu child);

    void delete(String name);

    void showMenu();

}
