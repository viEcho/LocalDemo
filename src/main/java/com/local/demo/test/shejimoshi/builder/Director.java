package com.local.demo.test.shejimoshi.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1("Part1");
        builder.buildPart2("Part2");
        builder.buildPart3("Part3");
    }
}