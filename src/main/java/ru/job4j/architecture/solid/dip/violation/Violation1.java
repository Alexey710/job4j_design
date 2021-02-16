package ru.job4j.architecture.solid.dip.violation;

public class Violation1 {
    private Print pr;

    public Print getPr() {
        return pr;
    }

    class Print {
        void print(String text) {
            System.out.println(text);
        }
    }
}
