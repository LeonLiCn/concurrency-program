package com.yang.lock.base.pool;

import java.util.concurrent.*;

public class ThreadPool {

    private static ThreadPoolExecutor tr = new ThreadPoolExecutor(5, 15, 100000,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
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
