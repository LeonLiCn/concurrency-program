package com.yang.lock.base.utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("parser1 finish");

        });

        Thread t2 = new Thread(() -> {
            System.out.println("parser2 finish");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("parser3 finish");
        });
/*
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(" All finish");*/

        new Thread(() -> {
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
            System.out.println(3);
            c.countDown();
        }).start();
        c.await();
        System.out.println("OK");

        t1.start();
        c.countDown();
        t2.start();
        c.countDown();
        t3.start();
        c.countDown();
        c.await();
        System.out.println("OJ*K");
    }

}
