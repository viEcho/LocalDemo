package com.local.demo.test.shejimoshi.builder;

public class ClientMain {
    public static void main(String[] args) {
        Builder builder = new A1Builder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
        System.out.println(product);
    }
}