package ru.job4j.architecture.solid.ocp.violation;

public class ViolationOCP {
    class Parent {
        private int a;

        public int print() {
            return a++;
        }

    }

    class Child extends Parent {
        private int b;

        @Override
        public int print() {
            return b++;
        }
    }

}
