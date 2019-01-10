package com.yang.lock.base.Concurrent;

import java.util.HashMap;
import java.util.UUID;

public class HashMapTest {

    public static void main(String[] args) throws InterruptedException {

        final HashMap<String, String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "fff" + i).start();
                }
            }
        }, "fff" );

        t.start();
        t.join();
    }

}
