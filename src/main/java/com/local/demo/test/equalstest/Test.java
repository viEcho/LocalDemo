package com.local.demo.test.equalstest;

/**
 * TODO 添加注释
 *
 * @author: echo
 * @date: 2025/3/2
 */
public class Test {


    public static void main(String[] args) {
        // ==比较的是两个存在于堆里不同的对象
        String str1 = new String("java");
        String str2 = new String("java");
        System.out.println("\nstr1 == str2: "+(str1 ==str2)); // 输出: false
        System.out.println("str1 str2 equals "+(str1.equals(str2))); // 输出: true

        // ==比较的是两个存在于常量池里相同的对象
        String str3 = "java";
        String str4 = "java";
        System.out.println("\nstr3 == str4: "+(str3 ==str4)); // 输出: true
        System.out.println("str3 str4 equals: "+(str3.equals(str4))); // 输出: true

        // ==比较的是常量池和堆里的两个不同的对象
        String str5 = "java";
        String str6 = new String("ja") + new String("va");
        System.out.println("\nstr5 == str6: "+(str5 ==str6)); // 输出: false
        System.out.println("str5 str6 equals: "+(str5.equals(str6))); // 输出: true

        // 编译器会优化 str8直接会拼接成一个常量
        String str7 = "java";
        String str8 = "ja" + "va";
        System.out.println("\nstr7 == str8: "+(str7 ==str8)); // 输出: true
        System.out.println("str7 str8 equals: "+(str7.equals(str8))); // true
    }


}
