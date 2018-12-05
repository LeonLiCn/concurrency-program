package com.yang.lock.base;

import java.util.concurrent.TimeUnit;

public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    protected Long initiaValue() {
        return System.currentTimeMillis();
    }

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Cost：" + Profiler.end() + "mills");
    }


}
