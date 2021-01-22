package ru.job4j.io.gson;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class LoopingGSON {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));
    }
}

class A {
    private B b;

    /*@JSONPropertyIgnore*/
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}

class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}


