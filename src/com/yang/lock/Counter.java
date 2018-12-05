package com.yang.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger ati = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {

        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.i);
        System.out.println(counter.ati.get());
        System.out.println(System.currentTimeMillis() - start);


    }

    private void safeCount() {
        for (; ; ) {

            int i = ati.get();
            boolean f = ati.compareAndSet(i, i++);
            if (f) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }


}
