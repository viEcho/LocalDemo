package com.local.test.mathCalculate;

import java.util.Arrays;

/**
 * @description: 二分查找算法
 * @author: echo
 * @date: 2023/2/15
 */
public class ErFenChaZhao {

    public static void main(String[] args) {
        int target = 9;
        int[] nums = new int[]{9,3,4,6,8};
        Arrays.sort(nums);
        System.out.println("排序后的nums为：" + Arrays.toString(nums));
        int result = binarySearch1(target,nums);
        System.out.println("非递归算法，目标" + target + "的下标是：" + result);
    }

    /**
     * 二分查找算法第一种：有序数组中没有重复的目标值（目标值是唯一的）---非递归
     * @param target
     * @param nums
     * @return
     */
    public static int binarySearch1(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;  //取最后一个下标
        int mid = 0;
        if (left > right || nums.length == 0 || nums == null) { //左下标大于右下标，直接返回-1
            return -1;
        }
        while (left <= right) { //初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length
            mid = (left + right) / 2; //如果下标之和除以2有小数，则直接去掉0.5
            if (target == nums[mid]) {
                return mid; //找到目标值，然后返回
            } else if (target > nums[mid]) {    //目标值大于中间值，向右遍历
                left = mid + 1; //所以向右遍历的第一个下标是：中间下标+1
            } else if (target < nums[mid]) {    //目标值小于中间值，向左遍历
                right = mid - 1;    //所以向左遍历的最后一个下标是：中间下标-1
            }
        }
        return -1;  //找不到对应目标值，直接返回-1
    }
}
