package ru.job4j.architecture.solid.srp.food;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;

public class ControlQualityTest {
    private final ControlQuality cq = new ControlQuality();

    @Test
    public void whenDistributeFoodTo25() {
        Food milk = new Milk("milk",
                LocalDate.of(2021, 2, 10),
                LocalDate.of(2021, 3, 14),
                54d, 0);
        /*CurrentDate set for test only*/
        milk.setCurrentDate(LocalDate.of(2021, 2, 14));
        cq.distributeFood(milk);
        Assert.assertEquals(cq.getStores().get(0).getList().size(), 1);
    }

    @Test
    public void whenDistributeFoodFrom25To75() {
        Food milk = new Milk("milk",
                LocalDate.of(2021, 1, 28),
                LocalDate.of(2021, 2, 28),
                54d, 0);
        /*CurrentDate set for test only*/
        milk.setCurrentDate(LocalDate.of(2021, 2, 14));
        cq.distributeFood(milk);
        Assert.assertEquals(cq.getStores().get(1).getList().size(), 1);
    }

    @Test
    public void whenDistributeFoodFrom75To100() {
        Food milk = new Milk("milk",
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 16),
                100d, 0);
        /*CurrentDate set for test only*/
        milk.setCurrentDate(LocalDate.of(2021, 2, 14));
        cq.distributeFood(milk);
        Assert.assertEquals(cq.getStores().get(1).getList().size(), 1);
        Assert.assertThat(cq.getStores().get(1).getList().get(0).getPrice(), is(100 * 0.6));
    }

    @Test
    public void whenDistributeFoodFrom100() {
        Food milk = new Milk("milk",
                LocalDate.of(2021, 2, 1),
                LocalDate.of(2021, 2, 12),
                100d, 0);
        /*CurrentDate set for test only*/
        milk.setCurrentDate(LocalDate.of(2021, 2, 14));
        cq.distributeFood(milk);
        Assert.assertEquals(cq.getStores().get(2).getList().size(), 1);
    }
}