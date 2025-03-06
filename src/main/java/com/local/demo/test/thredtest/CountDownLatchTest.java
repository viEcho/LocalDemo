package com.local.demo.test.thredtest;

import java.util.concurrent.CountDownLatch;

/**
 * countdownlatch 让线程同时执行
 * @auther: echo
 * @date: 2025/3/1
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println(System.currentTimeMillis());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();

        Thread thread = new Thread();
        thread.wait();

    }
}
