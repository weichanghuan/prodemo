package com.test.aop.cglib;

public class TestService {

    public String sayHello(int i, String j) {
        System.out.println("hello world");
        System.out.println(i + "" + j);
        return "返回值";
    }

    public String sayHello2(int i, String j) {
        System.out.println("hello world2");
        System.out.println(i + "" + j);
        return "返回值";
    }
}
