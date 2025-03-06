package com.local.demo.test.shejimoshi.proxy;

public class ClientMain {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);

        proxy.request();
    }
}