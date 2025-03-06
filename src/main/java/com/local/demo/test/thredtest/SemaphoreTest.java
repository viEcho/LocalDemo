package com.local.demo.test.thredtest;

import java.util.concurrent.Semaphore;


/**
 * semaphore 控制线程交替执行
 *
 * @author: echo
 * @date: 2025/3/1
 */
public class SemaphoreTest {
    private static final Semaphore s1 = new Semaphore(1);
    private static final Semaphore s2 = new Semaphore(1);
    private static final Semaphore s3 = new Semaphore(1);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            s2.acquire();
            s3.acquire();

            new Thread(() -> {
                try {
                    s1.acquire();
                    System.out.println("a 先跑");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    s2.release();
                }
            }).start();

            new Thread(() -> {
                try {
                    s2.acquire();
                    System.out.println("b 再跑");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    s3.release();
                }
            }).start();

            new Thread(() -> {
                try {
                    s3.acquire();
                    System.out.println("c 最后跑 \n");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    s1.release();
                    s2.release();
                    s3.release();
                }
            }).start();
        }
    }
}
