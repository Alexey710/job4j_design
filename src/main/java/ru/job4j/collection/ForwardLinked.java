package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleted = head.value;
        Node<T> second = head.next;
        head.next = null;
        head = second;
        return deleted;

    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleted = head.value;
        Node<T> tail = head;
        if (tail.next != null) {
            while (tail.next.next != null) {
                tail = tail.next;
            }
            deleted = tail.next.value;
            tail.next = null;
        } else {
            head = null;
            return deleted;
        }
        return deleted;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
