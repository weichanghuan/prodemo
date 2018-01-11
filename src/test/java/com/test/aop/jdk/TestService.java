package com.test.aop.jdk;

public class TestService implements ITestService {

    public String sayHello(int i, String j) {
        System.out.println("hello world");
        System.out.println(i + "" + j);
        return "返回值";
    }

}
