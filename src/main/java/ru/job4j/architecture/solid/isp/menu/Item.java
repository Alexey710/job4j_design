package ru.job4j.architecture.solid.isp.menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Item implements Menu {
    private Action action;
    private final String name;
    private final List<Item> list = new ArrayList<>();

    public Item(String root, Action action) {
        this.name = root;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public List<Item> getList() {
        return list;
    }

    @Override
    public void addItem(Item child) {
        list.add(child);
    }

    @Override
    public void showMenu() {
        Queue<Item> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            Item head = data.poll();
            System.out.println(head.getName());
            if (head.getList().size() != 0) {
                for (Item item : head.getList()) {
                    item.showMenu();
                }
            }
        }
    }

    @Override
    public void delete(String name) {
        Queue<Item> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            Item head = data.poll();
            if (head.getName().equals(name)) {
                this.getList().remove(head);
                break;
            }
            data.addAll(head.getList());
        }
    }

    @Override
    public void showSubmenu(String name) {
        Queue<Item> data = new LinkedList<>();
        data.offer(this);
        while (!data.isEmpty()) {
            Item head = data.poll();
            if (head.getName().equals(name)) {
                head.showMenu();
                break;
            }
            data.addAll(head.getList());
        }
    }

    public static void main(String[] args) {
        Item root = new Item("Задача 1.", new Action1());
        Item m2 = new Item("---- Задача 1.1.", new Action1());
        Item m3 = new Item("--------- Задача 1.1.1.", new Action2());
        Item m4 = new Item("--------- Задача 1.1.2.", new Action2());
        Item m5 = new Item("---- Задача 1.2.", new Action1());
        root.addItem(m2);
        m2.addItem(m3);
        m2.addItem(m4);
        root.addItem(m5);
        System.out.println("Вывести меню на экран ввиде дерева============================");
        root.showMenu();
        System.out.println("При выборе пункта можно удалить этот пункт====================");
        root.delete("---- Задача 1.2.");
        root.showMenu();
        System.out.println("При выборе пункта можно развернуть ветку этого пункта=========");
        root.showSubmenu("---- Задача 1.1.");
        System.out.println("При выборе пункта можно развернуть ветку этого пункта=========");
        root.showSubmenu("---- Задача 1.1.");
        System.out.println("У каждого пункта меню есть свое действие Action===============");
        root.action.doAction();
    }
}
