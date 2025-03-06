package com.local.demo.test.shejimoshi.proxy;

public class Proxy implements Subject {
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        System.out.println("Proxy: Checking access before handling request.");
        realSubject.request();
        System.out.println("Proxy: Logging the time of request.");
    }
}