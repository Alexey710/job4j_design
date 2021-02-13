package ru.job4j.architecture.solid.ocp.violation;

public class ViolationOCP3 {
    class Parent {
        private int a;

        public int compare() {
            if (a > 0) {
                return a;
            }
            return a;
        }

    }

    class Child extends Parent {
        private int a;

        @Override
        public int compare() {
            if (a < 0) {
                return a;
            }
            return 1;
        }
    }

}
