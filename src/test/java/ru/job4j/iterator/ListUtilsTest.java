package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 2, 1);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> rsl = ListUtils.removeIf(input, o -> o == 1);
        assertThat(rsl, Is.is(List.of(2, 3)));
    }

    @Test
    public void replaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> rsl = ListUtils.replaceIf(input, o -> o > 1, 0);
        assertThat(rsl, Is.is(List.of(1, 0, 0)));
    }

    @Test
    public void removeAll() {
        List<Integer> input = new ArrayList<>(List.of(3, 2, 3, 4));
        List<Integer> deleted = new ArrayList<>(List.of(3, 4));
        List<Integer> rsl = ListUtils.removeAll(input, deleted);
        assertThat(rsl, Is.is(List.of(2)));
    }
}