package com.exception;

import java.util.List;

/**
 * @Author: Wch
 * @Date 2018/6/7
 */
public class TestException {

    public void TestRuntimeException() {
        List list=null;
        list.get(0);
        System.out.println("2");
    }

    public void testException() {
       int i=1/0;

        System.out.println("1");
    }

    public static void main(String[] args) {
        TestException testException = new TestException();
        testException.testException();
        testException.TestRuntimeException();


    }

}
