package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo3 {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {

            ex.execute(new ParseDateTest(i));
        }
    }

    static class ParseDateTest implements Runnable {

        int i = 0;

        public ParseDateTest(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Date t = sdf.parse("2017-06-19 00:00:" + i % 60);
                System.out.println("==>i=" + i + ",t=" + t);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
