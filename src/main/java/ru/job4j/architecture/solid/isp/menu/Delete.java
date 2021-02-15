package ru.job4j.architecture.solid.isp.menu;

public class Delete implements Strategy {

    @Override
    public void doOperation(ItemMenu item, String name) {
        item.delete(name);
    }
}
