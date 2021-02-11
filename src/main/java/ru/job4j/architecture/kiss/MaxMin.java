package ru.job4j.architecture.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    private <T> T liken(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T max = null;
        for (T elem : value) {
            if (max == null) {
                max = elem;
            }
            int comp = comparator.compare(max, elem);
            if (predicate.test(comp)) {
                max = elem;
            }
        }
        return max;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return liken(value, comparator, p -> p < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return liken(value, comparator, p -> p > 0);
    }

    public static void main(String[] args) {
        int rsl = new MaxMin().min(List.of(5, 1, 2, 3), new ComparatorInt());
        System.out.println(rsl);
    }
}
