package ru.job4j.collection.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddDuplicateChild() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertFalse(tree.add(1, 3));
    }

    @Test
    public void whenAddDuplicateSubChild() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(2, 3));
        assertFalse(tree.add(2, 3));
    }

    @Test
    public void whenIsBinaryTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenIsBinaryFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertFalse(tree.isBinary());
    }
}