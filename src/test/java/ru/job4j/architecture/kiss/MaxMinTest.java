package ru.job4j.architecture.kiss;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class MaxMinTest {

    @Test
    public void max() {
        int rsl = new MaxMin().min(List.of(5, 1, 2, 3), new ComparatorInt());
        Assert.assertEquals(rsl, 1);
    }

    @Test
    public void min() {
        int rsl = new MaxMin().max(List.of(5, 1, 2, 3), new ComparatorInt());
        Assert.assertEquals(rsl, 5);
    }
}