package com.local.demo.test.shejimoshi.factory;

public class HtmlParserFactory implements ParserFactory{
    @Override
    public Parser createParser() {
        return new HtmlParser();
    }
}
