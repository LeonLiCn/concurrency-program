package com.yang.lock.base;

import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        // sleepThread不停的尝试睡眠
        Thread t1 = new Thread(new SleepThread(), "SleepThread");
        // busyThread不停的运行
        Thread t2 = new Thread(new BusyThread(), "BusyThread");
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();

        // 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        t1.interrupt();
        t2.interrupt();

        System.out.println("SleepThread interrupted is:" + t1.isInterrupted());
        System.out.println("BusyThread interrupted is:" + t2.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        TimeUnit.SECONDS.sleep(2);
    }

    static class SleepThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                ThreadState.second(10);
            }
        }
    }

    static class BusyThread implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }


}
