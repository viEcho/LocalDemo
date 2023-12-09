package com.local.demo.test.shejimoshi.singleton;

/**
 * @description: 单例模式-饿汉式
 * @author: echo
 * @date: 2023/2/15
 */
public class SingleTon1 {

    private static SingleTon1 singleTon = new SingleTon1();

    private SingleTon1(){

    }

    public static SingleTon1 getInstance(){
        return singleTon;
    }
    public void doSomeThing(){

    }
}


