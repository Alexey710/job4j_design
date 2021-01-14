package ru.job4j.collection.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> optChild = findBy(child);
        if (optChild.isPresent()) {
            return false;
        }
        Optional<Node<E>> optParent = findBy(parent);
        if (optParent.isPresent()) {
            Node<E> nodeParent = optParent.get();
            nodeParent.getChildren().add(new Node<E>(child));
            rsl = true;
        }
        return rsl;
    }

    public Optional<Node<E>> find(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        return find(o -> o.getChildren().size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return find(o -> o.getValue().equals(value));
    }

}