package com.yang.lock.base.utils;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BankWaterService implements Runnable {

    private CyclicBarrier cr = new CyclicBarrier(4, this);

    private Executor er = Executors.newFixedThreadPool(4);

    private Map<String, Integer> map = new ConcurrentHashMap<>();

    public void count() {
        for (int i = 0; i < 4; i++) {
            er.execute(() -> {
                map.put(Thread.currentThread().getName(), 1);

                try {
                    cr.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void run() {
        AtomicInteger result = new AtomicInteger(0);
        map.forEach((k, v) -> {
            int t = result.get();
            int i = result.get() + v;
            result.compareAndSet(t, i);
        });
        map.put("result", result.get());
        System.out.println(result.get());
    }

    static CyclicBarrier ir = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        BankWaterService service = new BankWaterService();
        service.count();

        Thread t = new Thread(() -> {
            try {
                ir.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.interrupt();

        try {
            ir.await();
        } catch (Exception e) {
            System.out.println(ir.isBroken());
            e.printStackTrace();

        }
    }
}
