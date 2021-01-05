package ru.job4j.collection.linkedlist;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;
    private int size;

    public Node<E> getFirst() {
        return first;
    }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return size;
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
        Objects.checkIndex(index, size);
        int count = 0;
        Node<E> current = first;
        while (current.next != null && count != index) {
            current = current.next;
            count++;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorForLinkedList<E>(this, modCount);
    }

    static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }
    }

}
