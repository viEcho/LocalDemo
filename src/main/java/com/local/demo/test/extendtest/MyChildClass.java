package com.local.demo.test.extendtest;

/**
 * 子类
 *
 * @author: echo
 * @date: 2025/3/1
 */
public class MyChildClass extends MyClass{

    public static void main(String[] args) throws InterruptedException {
        MyChildClass myChildClass = new MyChildClass();
        // 显示继承的方法
        myChildClass.test();

        MyClass myClass = new MyClass();
        // 隐示继承的方法
        myClass.wait();

        // 通过父类隐式继承的方法
        myChildClass.wait();
    }
}
