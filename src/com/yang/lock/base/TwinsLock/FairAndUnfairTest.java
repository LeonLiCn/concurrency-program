package com.yang.lock.base.TwinsLock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unfair() {
        testLock(unfairLock);
    }


    private void testLock(Lock lock) {
        for (int i = 0; i < 5; i++) {
            Job worker = new Job(lock);
            worker.setDaemon(true);
            worker.start();
        }
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        public void run() {
            System.out.println("lock by:"+ Thread.currentThread().getName() );
            System.out.println("lock by:"+ Thread.currentThread().getName()  );
        }

    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean flag) {
            super(flag);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> list = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }



}
