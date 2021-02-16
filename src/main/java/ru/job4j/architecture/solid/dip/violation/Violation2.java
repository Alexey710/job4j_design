package ru.job4j.architecture.solid.dip.violation;

public class Violation2 {
    private Violation1 violation1;

    public Violation2(Violation1 violation1) {
        this.violation1 = violation1;
    }

    void someMethod(String text) {
        violation1.getPr().print(text);
    }
}
