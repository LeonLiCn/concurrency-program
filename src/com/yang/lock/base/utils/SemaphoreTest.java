package com.yang.lock.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService tp = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore e = new Semaphore(10);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            tp.execute(() -> {
                try {
                    e.acquire();
                    System.out.println("save data");
                    e.release();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            });
        }

        tp.shutdown();

    }


}
