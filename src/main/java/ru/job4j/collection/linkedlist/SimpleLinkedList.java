package ru.job4j.collection.linkedlist;

import java.util.Iterator;

public class SimpleLinkedList<E> implements Iterable {
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;
    private int size;

    public int getModCount() {
        return modCount;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    E get(int index) {
        int count = 0;
        Node<E> current = first;
        while (current.next != null && count != index) {
            current = current.next;
            count++;
        }
        return current.item;
    }

    @Override
    public Iterator iterator() {
        return new IteratorForLinkedList(this, modCount);
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
