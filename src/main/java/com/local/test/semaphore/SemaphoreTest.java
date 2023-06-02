package com.local.test.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: 信号量
 * @author: echo
 * @date: 2023/2/13
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Parking parking = new Parking(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                parking.park();
            }).start();
        }
    }

    static class Parking {
        Semaphore semaphore;

        public Parking(int count) {
            this.semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName()
                        + "--->>>进入停车场");
                TimeUnit.SECONDS.sleep(time);
                System.out.println(Thread.currentThread().getName() + " 开出停车场，，停车" + time + "秒...--->>>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
