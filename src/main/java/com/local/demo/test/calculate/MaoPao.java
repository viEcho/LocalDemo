package com.local.demo.test.calculate;

import java.util.Arrays;

/**
 * @description: 冒泡算法
 * @author: echo
 * @date: 2023/2/15
 */
public class MaoPao {

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 3, 5, 6, 0, 9, 34, 22, 8};
        boolean flag = true;                        //用于优化循环次数，当十个元素，正常需要冒泡九次，但是如果数组在冒泡第五次的时候顺序就已经是从小到大排序好了，后面的循环就多余了
        int temp = 0;                               //相邻元素交换，存储交换值
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {   //-i-i： 每冒泡一次，都会确定一次最大的元素，放在最后一位，所以每次内循环就可以减少一次循环次数
                if (array[j] > array[j + 1]) {        //冒泡
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(array));    //每次冒泡，输出本次结果
            count++;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("冒泡次数：" + count);
    }
}
