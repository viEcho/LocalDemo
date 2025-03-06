package com.local.demo.test.shejimoshi.builder;

public interface Builder {
    void buildPart1(String part1);
    void buildPart2(String part2);
    void buildPart3(String part3);
    Product getResult();
}