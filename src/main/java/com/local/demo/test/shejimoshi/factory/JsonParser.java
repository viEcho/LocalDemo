package com.local.demo.test.shejimoshi.factory;

public class JsonParser implements Parser{
    @Override
    public String parse(String content) {
        return "Json Parsed: " + content;
    }
}
