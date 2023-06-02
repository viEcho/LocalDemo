package com.local.test.shejimoshi;

/**
 * @description: 单例模式-静态内部类
 * @author: echo
 * @date: 2023/2/15
 */
public class SingleTon4 {
    // 私有静态内部类
    private static class InnerInstance{
        private static final SingleTon4 singleton = new SingleTon4();
    }
    // 对外提供的初始化方法
    public static SingleTon4 getInstance(){
        return InnerInstance.singleton;
    }
    // 私有构造器
    private SingleTon4(){

    }
    public void doSomeThing(){
        System.out.println("do some thing!");
    }
}

