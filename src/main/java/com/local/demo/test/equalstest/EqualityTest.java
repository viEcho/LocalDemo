package com.local.demo.test.equalstest;

import java.util.Objects;

/**
 * TODO 添加注释
 *
 * @author: echo
 * @date: 2025/3/2
 */
public class EqualityTest {
    public static void main(String[] args) {
        int x = 10;
        int y = 10;
        System.out.println("x == y: " + (x == y)); // 输出: true
        System.out.println("x y equals " + (Objects.equals(x,y))); // 输出: true

        int i = 168;
        int j = 168;
        System.out.println("\ni == j: " + (i == j)); // 输出：true
        System.out.println("i j equals: " + (Objects.equals(i,j))); // 输出：true

        Integer a = 18;
        Integer b = 18;
        System.out.println("\na == b: " + (a == b));// 输出true
        System.out.println("a b equals: " + (a.equals(b)));//输出true

        Integer a1 = new Integer(33);
        Integer b1 = new Integer(33);
        System.out.println("\na1 == b1: " + (a1 == b1));// 输出false
        System.out.println("a1 b1 equals: " + (a1.equals(b1)));// 输出true

        Integer a2 = 168;
        Integer b2 = 168;
        System.out.println("\na2 == b2: " + (a2 == b2));// 输出false
        System.out.println("a2 b2 equals: " + (a2.equals(b2))); //输出true

        Integer a3 = new Integer(200);
        Integer b3 = new Integer(200);
        System.out.println("\na3 == b3: " + (a3 == b3));// 输出false
        System.out.println("a3 b3 equals: " + (a3.equals(b3)));// 输出true

        String str1 = new String("test");
        String str2 = new String("test");
        System.out.println("\nstr1 == str2: " + (str1 == str2)); // 输出: false
        System.out.println("str1 str2 equals " + (str1.equals(str2))); // 输出: true

        String str3 = "ask";
        String str4 = "ask";
        System.out.println("\nstr3 == str4: " + (str3 == str4)); // 输出: true
        System.out.println("str3 str4 equals: " + (str3.equals(str4))); // 输出: true

        String str5 = "java";
        String str6 = new String("ja")+ new String("va");
        System.out.println("\nstr5 == str6: " + (str5 == str6)); // 输出: false
        System.out.println("str5 str6 equals: " + (str5.equals(str6))); // 输出: true

        String str7 = "python";
        String str8 = "py" + "thon";
        System.out.println("\nstr7 == str8: " + (str7 == str8)); // 输出: true
        System.out.println("str7 str8 equals: " + (str7.equals(str8))); // true
    }
}
