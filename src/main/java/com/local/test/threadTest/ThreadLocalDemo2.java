package com.local.test.threadTest;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程间隔离
 * @author: echo
 * @date: 2023/2/18
 */
public class ThreadLocalDemo2 {
    static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set("thread1");
            System.out.println("thread1 get:"+threadLocal.get());
        },"thread1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2 get:"+threadLocal.get());
        },"thread2").start();
    }
}
