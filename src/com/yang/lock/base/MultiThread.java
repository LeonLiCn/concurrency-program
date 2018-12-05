package com.yang.lock.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

    public static void main(String[] args) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = bean.dumpAllThreads(false, false);
        for (ThreadInfo info : infos) {
            System.out.println("[" + info.getThreadId() +"]" + info.getThreadName());
        }
    }

}

