package com.yang.lock.base.TwinsLock;

import com.yang.lock.base.ThreadState;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {

    /**
     * 运行该测试用例，可以看到线程名称成对输出，也就是在同一时刻只有两个线程能够获 取到锁
     * 这表明TwinsLock可以按照预期正确工作。
     */
    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        ThreadState.second(1);
                        System.out.println(Thread.currentThread().getName());
                        ThreadState.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        //每隔1秒换行
        for (int i = 0; i < 10; i++) {
            ThreadState.second(1);
            System.out.println();
        }

    }

}
