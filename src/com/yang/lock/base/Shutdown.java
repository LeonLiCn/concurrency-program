package com.yang.lock.base;

import java.util.concurrent.TimeUnit;

public class Shutdown {

    public static void main(String[] args) throws InterruptedException {

        Runner r = new Runner();
        Thread t = new Thread(r, "count");
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
        Runner w = new Runner();
        t = new Thread(w, "count");
        t.start();

        TimeUnit.SECONDS.sleep(1);
        w.cancel();
    }

    private static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }


}
