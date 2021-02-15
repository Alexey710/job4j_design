package ru.job4j.architecture.solid.isp.menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ItemMenu implements Menu {
    private String name;
    private final List<ItemMenu> list = new ArrayList<>();

    public ItemMenu(String root) {
        this.name = root;
    }

    public String getName() {
        return name;
    }

    public List<ItemMenu> getList() {
        return list;
    }

    @Override
    public void addItem(ItemMenu child) {
        list.add(child);
    }

    @Override
    public boolean deleteItem(String name) {
        return false;
    }

    @Override
    public void showMenu() {
        Queue<ItemMenu> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            ItemMenu head = data.poll();
            System.out.println(head.getName());
            if (head.getList().size() != 0) {
                for (ItemMenu item : head.getList()) {
                    item.showMenu();
                }
            }
        }
    }

    public void delete(String name) {
        Queue<ItemMenu> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            ItemMenu head = data.poll();
            if (head.getName().equals(name)) {
                this.getList().remove(head);
                break;
            }
            data.addAll(head.getList());
        }
    }

    public void showSubmenu(String name) {
        Queue<ItemMenu> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            ItemMenu head = data.poll();
            if (head.getName().equals(name)) {
                head.showMenu();
                break;
            }
            data.addAll(head.getList());
        }
    }

    public static void main(String[] args) {
        ItemMenu root = new ItemMenu("Задача 1.");
        ItemMenu m2 = new ItemMenu("---- Задача 1.1.");
        ItemMenu m3 = new ItemMenu("--------- Задача 1.1.1.");
        ItemMenu m4 = new ItemMenu("--------- Задача 1.1.2.");
        ItemMenu m5 = new ItemMenu("---- Задача 1.2.");
        root.addItem(m2);
        m2.addItem(m3);
        m2.addItem(m4);
        root.addItem(m5);
        System.out.println("Вывести меню на экран ввиде дерева============================");
        root.showMenu();
        System.out.println("При выборе пункта можно удалить этот пункт====================");
        Context context = new Context(new Delete());
        context.executeStrategy(root, "---- Задача 1.2.");
        root.showMenu();
        System.out.println("При выборе пункта можно развернуть ветку этого пункта=========");
        Context context1 = new Context(new ShowSubmenu());
        context1.executeStrategy(root, "---- Задача 1.1.");
    }
}
