package ru.job4j.architecture.solid.ocp.violation;

public class ViolationOCP2 {
    class Parent {
        private int a;

        public int back(int x) {
            return 100 + a;
        }

    }

    class Child extends Parent {
        private int b;

        @Override
        public int back(int x) {
            return 100 + b;
        }
    }

}
