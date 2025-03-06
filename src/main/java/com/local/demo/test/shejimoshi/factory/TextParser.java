package com.local.demo.test.shejimoshi.factory;

public class TextParser implements Parser{
    @Override
    public String parse(String content) {
        return "Text Parsed: " + content;
    }
}
