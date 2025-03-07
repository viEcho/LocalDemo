package com.local.demo.test.shejimoshi.builder;

public class A1Builder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1(String part1) {
        product.setPart1(part1);
    }

    @Override
    public void buildPart2(String part2) {
        product.setPart2(part2);
    }

    @Override
    public void buildPart3(String part3) {
        product.setPart3(part3);
    }

    @Override
    public Product getResult() {
        return product;
    }
}