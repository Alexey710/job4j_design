package ru.job4j.architecture.solid.isp.menu;

public interface Menu {
    void addItem(Item child);

    void delete(String name);

    void showMenu();

    void showSubmenu(String name);

    void doActionByName(String name);
}
