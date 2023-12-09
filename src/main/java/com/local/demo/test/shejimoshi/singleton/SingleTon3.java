package com.local.demo.test.shejimoshi.singleton;

/**
 * @description: 单例模式-线程安全的懒汉式
 * @author: echo
 * @date: 2023/2/15
 */
public class SingleTon3 {
    private volatile static SingleTon3 singleton3;

    // 对外提供初始化方法
    public  static SingleTon3 initInstance(){
        if(singleton3 == null){
            synchronized(SingleTon3.class){
                if(singleton3 == null)
                    singleton3 = new SingleTon3();
            }
        }
        return singleton3;
    }

    // 私有构造器
    private SingleTon3(){

    }

    public void doSomeThing(){
        System.out.println("do some thing!");
    }
}
