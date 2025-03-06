package com.local.demo.test.shejimoshi.factory;

public class HtmlParser implements Parser{
    @Override
    public String parse(String content) {
        return "Html Parsed: " + content;
    }
}
