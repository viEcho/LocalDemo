package com.local.demo.test.shejimoshi.factory;

public class TextParserFactory implements ParserFactory{
    @Override
    public Parser createParser() {
        return new TextParser();
    }
}
