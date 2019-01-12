package com.yang.lock.base.utils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    private static final Exchanger<String> es = new Exchanger<>();

    private static ExecutorService se = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        se.execute(() -> {
            String a = "这是a数据";
            try {
                String b = es.exchange(a);
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        se.execute(() -> {
            String b = "这是b数据";
            try {
                String a = es.exchange(b);
                System.out.println(a + "," + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        se.shutdown();
    }

}
