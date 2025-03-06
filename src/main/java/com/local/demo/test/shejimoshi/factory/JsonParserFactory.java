package com.local.demo.test.shejimoshi.factory;

public class JsonParserFactory implements ParserFactory{
    @Override
    public Parser createParser() {
        return new JsonParser();
    }
}
