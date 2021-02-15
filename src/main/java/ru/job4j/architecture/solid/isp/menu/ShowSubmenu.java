package ru.job4j.architecture.solid.isp.menu;

public class ShowSubmenu implements Strategy {

    @Override
    public void doOperation(ItemMenu item, String name) {
        item.showSubmenu(name);
    }
}