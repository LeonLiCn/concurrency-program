package com.yang.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    private void deadLock() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Lock lock = new ReentrantLock();
                try {
                    lock.tryLock(3000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println(111);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println(222);
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {

        new DeadLockDemo().deadLock();


    }


}
