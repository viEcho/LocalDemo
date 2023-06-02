package com.local.test.extendsTest;

import java.util.*;

/**
 * @description: 探究泛型边界问题
 * @author: echo
 * @date: 2023/2/3
 */
public class Test1 {

    public static void main(String[] args) {
        int i =128;
        Integer j = 128;
        boolean equals = Objects.equals(i, j);

        System.out.println(equals);

        int i1 = Objects.hashCode(i);
        int j1 = Objects.hashCode(j);

        System.out.println(i1);
        System.out.println(j1);

        Long a = 1111111111111111111L;


        List<? super Animal> list1 =new ArrayList<>();
        list1.add(new Dog());

        List<? extends Animal> list2 =new ArrayList<>();
        list2.get(0);
    }

}
