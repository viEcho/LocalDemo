package com.local.demo.test.thredtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * simpleDateFormat线程不安全的示例
 *
 * @author: echo
 * @date: 2025/3/6
 */
public class SimpleDateFormatThreadUnsafeDemo {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ThreadLocal<SimpleDateFormat> threadLocalSdf =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    public static void main(String[] args) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        String formattedDate = sdf.format(date);
        System.out.println("格式化后的日期main: " + formattedDate);

        // 立即解析刚格式化的字符串
        Date parsedDate = sdf.parse(formattedDate);
        System.err.println("立即解析刚格式化的字符串main: " + sdf.format(parsedDate));

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交多个格式化任务
        for (int i = 0; i < 10; i++) {
            //final int delta = i;
            executor.submit(() -> {
                try {
                    //Date date = new Date(System.currentTimeMillis() - delta * 60*60*1000);
                    Date date1 = new Date(System.currentTimeMillis());
                    String formattedDate1 = sdf.format(date1);
                    // String formattedDate1 = threadLocalSdf.get().format(date1);
                    System.out.println("格式化后的日期: " + formattedDate1);

                    Thread.sleep(2000);
                    // 立即解析刚格式化的字符串
                    Date parsedDate1 = sdf.parse(formattedDate);
                    //Date parsedDate1 = threadLocalSdf.get().parse(formattedDate);

                    // 检查结果是否一致
                    if (!formattedDate1.equals(sdf.format(parsedDate1))) {
                        System.err.println("立即解析刚格式化的字符串: " + sdf.format(parsedDate1));
                    }
                    //if (!formattedDate1.equals(threadLocalSdf.get().format(parsedDate1))) {
                        //System.err.println("立即解析刚格式化的字符串: " + threadLocalSdf.get().format(parsedDate1));
                    //}
                } catch (Exception e) {
                    System.err.println("解析异常: " + e.getMessage());
                }
            });
        }
        executor.shutdown();
    }
}

