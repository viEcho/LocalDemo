package com.local.demo.test.thredtest;

import java.util.concurrent.*;

/**
 * 模拟CyclicBarrier 的线程交替执行 然后执行完后同步等待其他线程执行完 然后接着汇总数据
 *
 * @author: echo
 * @date: 2025/3/1
 */
public class CyclicBarrierTest {
    // 阶段数
    private static final int PHASES = 2;
    // 线程数
    private static final int THREAD_NUM = 3;

    public static void main(String[] args) {
        // 创建可复用的屏障，指定线程数和阶段完成后的汇总操作
        CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, () -> {
            System.out.println("所有线程都已到达屏障开始放行，进行下一步汇总校验");
        });
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
        // 模拟2个处理阶段
        for (int phase = 1; phase <= PHASES; phase++) {
            System.out.println("\n ===== 开始阶段 " + phase + " =====");

            for (int i = 0; i < THREAD_NUM; i++) {
                final int threadId = i + 1;
                int finalPhase = phase;
                executor.submit(() -> {
                    processDataChunk(threadId, finalPhase);  // 处理数据分片
                    awaitBarrier(barrier, threadId);    // 等待其他线程
                });
            }
            // 重置屏障以复用（CyclicBarrier自动重置）
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
        executor.shutdown();
    }

    // 处理数据分片（模拟耗时操作）
    private static void processDataChunk(int threadId, int phase) {
        System.out.printf("线程-%d 正在处理阶段%d的数据...%n", threadId, phase);
        try {
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {
        }
    }

    // 等待屏障
    private static void awaitBarrier(CyclicBarrier barrier, int threadId) {
        try {
            System.out.printf("线程-%d 已到达屏障，等待其他线程...%n", threadId);
            barrier.await();
            System.out.printf("线程-%d 突破屏障，继续后续操作%n", threadId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
