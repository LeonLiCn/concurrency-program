package com.yang.lock.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {

    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread thread = new Thread(new Runner(), "PrintThread");
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(3);


        //暂停线程
        thread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

        //恢复线程
        thread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

         //中止线程
        thread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

    }

    static class Runner implements Runnable {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "Run at：" + format.format(new Date()));
                ThreadState.second(1);
            }
        }
    }



}
