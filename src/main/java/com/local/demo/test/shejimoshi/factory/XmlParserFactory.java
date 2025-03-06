package com.local.demo.test.shejimoshi.factory;

public class XmlParserFactory implements ParserFactory{
    @Override
    public Parser createParser() {
        return new XmlParser();
    }
}
