package com.local.test.threadTest;

/**
 * @description: 线程池
 * @author: echo
 * @date: 2023/2/9
 */
public class ThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("aaaaa");
        //int corePoolSize, // 核心池线程数大小 (常用)
        //int maximumPoolSize,  // 最大的线程数大小 (常用)
        //long keepAliveTime, // 超时等待时间 (常用)
        //TimeUnit unit, // 时间单位 (常用)
        //BlockingQueue<Runnable> workQueue, // 阻塞队列(常用)
        //ThreadFactory threadFactory, // 线程工厂
        //RejectedExecutionHandler handler // 拒绝策略(常用)


        new java.util.concurrent.ThreadPoolExecutor(10,
                10,1000,
                null,null,null,null
                );
    }

}
