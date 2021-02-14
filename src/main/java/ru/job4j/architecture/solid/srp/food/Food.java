package ru.job4j.architecture.solid.srp.food;

import java.time.LocalDate;

public class Food {
    private LocalDate currentDate = LocalDate.now();
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public long getPercentOfSpentDays() {
        long allDays = expiryDate.toEpochDay() - createDate.toEpochDay();
        long restOfDays = expiryDate.toEpochDay() - currentDate.toEpochDay();
        long percentOfSpentDays = 100 - (restOfDays * 100 / allDays);
        return percentOfSpentDays;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        price = price - (price / 100 * discount);
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
