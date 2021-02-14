package ru.job4j.architecture.solid.srp.food;

public class ControlQuality {
    private final int discount = 40;
    private Context context;
    private final Store shop;
    private final Store warehouse;
    private final Store trash;

    public ControlQuality() {
        shop = new Shop();
        trash = new Trash();
        warehouse = new Warehouse();
    }

    public void distributeFood(Food food) {
        long condition = food.getPercentOfSpentDays();
        if (condition < 25) {
            context = new Context(new AddWarehouse());
            context.executeStrategy(food, warehouse);
        } else if (condition >= 25 && condition < 75) {
            context = new Context(new AddShop());
            context.executeStrategy(food, shop);
        } else if (condition > 75 && condition < 100) {
            food.setDiscount(discount);
            context = new Context(new AddShop());
            context.executeStrategy(food, shop);
        } else if (condition >= 100) {
            context = new Context(new AddTrash());
            context.executeStrategy(food, trash);
        }
    }

    public Store getShop() {
        return shop;
    }

    public Store getWarehouse() {
        return warehouse;
    }

    public Store getTrash() {
        return trash;
    }
}