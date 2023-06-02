package com.local.test.threadTest;

/**
 * @description: 探究内存泄漏
 * @author: echo
 * @date: 2023/2/20
 */
public class ThreadLocalDemo3 {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("just do it");
        // threadLocal = null;
        threadLocal.remove();
        System.gc();
        Thread thread = Thread.currentThread();
        System.out.println(thread);
    }

}
