package com.local.demo.test.shejimoshi.factory;

public class ParserFactoryCreator {
    public static ParserFactory getFactory(String fileType) {
        switch (fileType.toLowerCase()) {
            case "xml":
                return new XmlParserFactory();
            case "json":
                return new JsonParserFactory();
            case "text":
                return new TextParserFactory();
            case "html":
                return new HtmlParserFactory();
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }
}
