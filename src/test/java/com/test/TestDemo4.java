package com.test;

public class TestDemo4 {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("这是run");
            }
        }.start();
    }

}
