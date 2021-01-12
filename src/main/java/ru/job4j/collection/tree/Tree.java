package ru.job4j.collection.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean isBinary() {
        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> currentNode = queue.remove();
            int size = currentNode.getChildren().size();
            if (size != 2 && size != 0) {
                return false;
            } else {
                queue.addAll(currentNode.getChildren());
            }
        }
        return true;
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

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
}