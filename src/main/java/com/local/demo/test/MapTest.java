package com.local.demo.test;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @description: hashMap 和hashTable的不同
 * @author: echo
 * @date: 2023/2/9
 */
public class MapTest {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap<>();
        hashMap.put("aaaa","sss");
//        hashMap.put(null,"sssss");
        hashMap.put("ssss",null);
        hashMap.put(null,null);

        System.out.println(hashMap.get(null));
        // hashMap key和value 都可以为null
        // 继承AbstractMap


        System.out.println(hashMap);

        Hashtable hashtable = new Hashtable();
        hashtable.put("a","ss");
        //hashtable.put(null,"ssss");//空指针异常
        //hashtable.put("ssss",null);//空指针异常
        // hashTable key 和value 都不能为null
        // 继承Dictionary

    }
}
