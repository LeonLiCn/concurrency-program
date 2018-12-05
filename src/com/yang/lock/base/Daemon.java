package com.yang.lock.base;

public class Daemon {

    public static void main(String[] args) {

        Thread t = new Thread(new DaemonRunner(), "DaemonThread");
        t.setDaemon(true);
        t.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                ThreadState.second(10);
            } finally {
                System.out.println("DaemonThread finally run");
            }
        }
    }

}
