package com.local.demo.test.shejimoshi.factory;

public class ClientMain {
    public static void main(String[] args) {
        // 根据文件类型获取工厂
        ParserFactory factory = ParserFactoryCreator.getFactory("xml");

        // 创建解析器并解析内容
        Parser parser = factory.createParser();
        String result = parser.parse("<root>data</root>");

        System.out.println(result);  // 输出: XML Parsed: <root>data</root>
    }
}
