package ru.job4j.architecture.solid.isp.menu;

public interface Menu {
    void addItem(ItemMenu child);

    boolean deleteItem(String name);

    void showMenu();

}
