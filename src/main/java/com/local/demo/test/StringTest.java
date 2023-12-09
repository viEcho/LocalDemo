package com.local.demo.test;

/**
 * @description: String StringBuffer StringBuild
 * @author: echo
 * @date: 2023/2/9
 */
public class StringTest {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("aaaaa");//线程安全

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ssss");//非线程安全

        String s1 = new String("java");
        String s2 = "java";
        System.out.println(s1==s2);//false

        String s3 = new String("ja")+new String("va");
        System.out.println(s2==s3);//false

        String s4 = new String("c") + new String("++");
        s4.intern();
        String s5 = "c++";
        System.out.println(s4 == s5);//true


        String s6 = "python";
        String s7 = new String("py")+new String("thon");
        System.out.println(s7.intern() == s6);;//true
        System.out.println(s7 == "python");//false

        String s8 = new String("go")+new String("lang");
        System.out.println(s8.intern() == s8);//true
        //s8.intern() 判断常量池中是否有golang对象，没有就直接指向s8,所以为true
        System.out.println(s8 == "golang");//true
        //s8 已经被执行堆中的golang了 所以为true
        System.out.println(s8.intern() == "golang");//true
        //和上面一样
    }
}
