package com.local.test.shejimoshi;

/**
 * @description: 单例-懒汉式
 * @author: echo
 * @date: 2023/2/15
 */
public class SingleTon2 {
    private static SingleTon2 singleTon2;

    private SingleTon2(){

    }

    public SingleTon2 getInstance(){
        if(null == singleTon2){
            singleTon2 = new SingleTon2();
        }
        return singleTon2;
    }

    public void doSomeThing(){
        System.out.println("do some thing!");
    }

}
