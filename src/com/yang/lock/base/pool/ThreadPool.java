package com.yang.lock.base.pool;

import java.util.concurrent.*;

public class ThreadPool {

    private static ThreadPoolExecutor tr = new ThreadPoolExecutor(5, 15, 100000,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    private static ExecutorService es = Executors.newFixedThreadPool(5);
    private static ExecutorService es1 = Executors.newSingleThreadExecutor();
    private static ExecutorService es2 = Executors.newCachedThreadPool();
    private static ExecutorService es3 = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) {

        FutureTask<String> ft = new FutureTask<>(() -> {
            return "hello";
        });
        ft.run();
        tr.execute(ft);
        try {
            ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Future<String> f = tr.submit(() -> {
            return "hello world";
        });
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            tr.shutdown();
        }

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
