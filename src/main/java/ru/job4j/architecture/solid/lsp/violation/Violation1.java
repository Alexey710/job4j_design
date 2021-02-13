package ru.job4j.architecture.solid.lsp.violation;

public class Violation1 {
    class Square {
        private int side1;
        private int side2;

        public int getSide1() {
            return side1;
        }

        public int getSide2() {
            return side2;
        }

        public double calculateArea() {
            return Math.pow(side1, 2);
        }
    }

    class Rectangle extends Square {
        @Override
        public double calculateArea() {
            return this.getSide1() * this.getSide2();
        }

    }
}
