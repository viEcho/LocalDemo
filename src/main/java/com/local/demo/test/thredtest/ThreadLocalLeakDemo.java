package com.local.demo.test.thredtest;

/**
 * 以下是一个演示 ThreadLocal 内存泄漏的代码示例，通过线程池复用线程未清理 ThreadLocal 导致 value 无法回收
 *
 * @author: echo
 * @date: 2025/3/6
 */

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalLeakDemo {
    static class HeavyObject {
        private final byte[] data = new byte[1024 * 1024 * 10]; // 10MB
    }

    public static void main(String[] args) throws Exception {
        // 使用单线程池确保复用同一个线程
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Thread[] workerThread = new Thread[1]; // 用于捕获工作线程

        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                workerThread[0] = Thread.currentThread(); // 记录工作线程
                ThreadLocal<HeavyObject> threadLocal = new ThreadLocal<>();
                threadLocal.set(new HeavyObject());
                System.out.println("[任务执行] 设置 ThreadLocal 值");
                // 注意：此处故意不调用 remove()
            });

            // 等待任务执行完成
            while (workerThread[0] == null) Thread.sleep(10);

            // 强制触发GC（需要JVM参数：-XX:+ExplicitGCInvokesConcurrent）
            System.gc();
            Thread.sleep(1000); // 等待GC完成

            // 打印工作线程的 ThreadLocalMap
            printStaleEntries(workerThread[0]);
        }
        executor.shutdown();
    }

    // 反射打印线程的 ThreadLocalMap 中残留的 HeavyObject
    private static void printStaleEntries(Thread thread) throws Exception {
        Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
        threadLocalsField.setAccessible(true);
        Object threadLocalMap = threadLocalsField.get(thread);

        int leakCount = 0;
        if (threadLocalMap != null) {
            Field tableField = threadLocalMap.getClass().getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] entries = (Object[]) tableField.get(threadLocalMap);

            for (Object entry : entries) {
                if (entry != null) {
                    Field valueField = entry.getClass().getDeclaredField("value");
                    valueField.setAccessible(true);
                    Object value = valueField.get(entry);
                    if (value instanceof HeavyObject) {
                        leakCount++;
                        System.out.println("发现内存泄漏对象: " + value.hashCode());
                    }
                }
            }
        }
        System.out.println("--当前线程残留 HeavyObject 数量: " + leakCount + "--\n");
    }
}
