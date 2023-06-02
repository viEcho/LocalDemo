package com.local.test.threadTest;

import com.local.test.threadTest.vo.UserInfo;
import com.sun.istack.internal.NotNull;

/**
 * @description: 避免传参耦合
 * @author: echo
 * @date: 2023/2/18
 */
public class ThreadLocalDemo1 {
    static  ThreadLocal threadLocal = new ThreadLocal();


    public static UserInfo getUserInfoFromLogin(@NotNull Long userId){
        return queryUserInfoFromDB(userId);
    }
    public static UserInfo queryUserInfoFromDB(Long userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName("同学："+userId);
        userInfo.setSex("男");
        return userInfo;
    }

    //--------参数传递
    public static void doSomeThing1(UserInfo userInfo){
        System.out.println("当前线程"+Thread.currentThread().getName()+
                "-我是doSomeThing1，判断("+userInfo.getUserName()+")是否是超级管理员");
        doSomeThing2(userInfo);
    }
    public static void doSomeThing2(UserInfo userInfo){
        System.out.println("当前线程"+Thread.currentThread().getName()+
                "-我是doSomeThing2,判断("+userInfo.getUserName()+")是否是统筹员");
        // ...doSomeThing3(userInfo)
    }

    //-------使用ThreadLocal 获取
    public static void doSomeThingA(){
        UserInfo userInfo= (UserInfo)threadLocal.get();
        System.out.println("当前线程"+Thread.currentThread().getName()+
                "-我是doSomeThingA，判断("+userInfo.getUserName()+")是否是超级管理员");
        // doSomeThingB();
    }
    public static void doSomeThingB(){
        UserInfo userInfo= (UserInfo)threadLocal.get();
        System.out.println("当前线程"+Thread.currentThread().getName()+
                "-我是doSomeThingB,判断("+userInfo.getUserName()+")是否是统筹员");
        // ...doSomeThingC()
    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            doSomeThing1(getUserInfoFromLogin(1L));
        },"Thread1").start();

        new Thread(()->{
            doSomeThing1(getUserInfoFromLogin(2L));
        },"Thread2").start();

        new Thread(()->{
            threadLocal.set(getUserInfoFromLogin(1L));
            doSomeThingA();
        },"Thread3").start();

        new Thread(()->{
            threadLocal.set(getUserInfoFromLogin(2L));
            doSomeThingA();
        },"Thread4").start();
    }
}
