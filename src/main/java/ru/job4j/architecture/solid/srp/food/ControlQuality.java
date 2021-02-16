package ru.job4j.architecture.solid.srp.food;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControlQuality {

    private Context context;
    private List<Store> stores;

    public ControlQuality() {
        stores = List.of(new Warehouse(), new Shop(), new Trash());
    }

    public List<Store> getStores() {
        return stores;
    }

    public void distributeFood(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                context = new Context(store.getStrategy());
                context.executeStrategy(food, store);
            }
        }
    }

    public void resort() {
        List<Food> tempStore = new ArrayList<>();
        for (Store store : stores) {
            List<Food> list = store.getList();
            Iterator<Food> it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                Food food = list.remove(index++);
                tempStore.add(food);
            }
        }
        for (Food food : tempStore) {
            distributeFood(food);
        }
    }

}