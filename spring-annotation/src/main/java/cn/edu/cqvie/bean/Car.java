package cn.edu.cqvie.bean;

public class Car {

    public Car() {
        System.out.println("car constructor ...");
    }

    public void init() {
        System.out.println("car ... init ...");
    }

    public void destroy() {
        System.out.println("car ... destroy ...");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}