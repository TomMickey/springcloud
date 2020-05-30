package com.grgbanking.test;

public class TestClass {
    public static void main(String[] args) {
        Direction d = Direction.FRONT;
        System.out.println(d.getName());
        System.out.println(d.name());
        System.out.println(Direction.values().length);
    }
}
