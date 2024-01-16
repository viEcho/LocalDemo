package com.local.demo.util;


import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 图像调整大小实用程序
 *
 * @author echo
 * @date 2024/01/05
 */
public class ImageResizeUtil {
    /**
     * main方法
     *
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        String originalPath = "/Users/echo/Desktop/sjms.png";
        int targetWidth = 960;
        int targetHeight = 266;
        // 30*1024字节 表示调整到30K
        long targetMemorySize = 66 * 1024L;
        String newPath = compressImage(originalPath, targetMemorySize);
        changeImageSize(newPath, targetWidth, targetHeight);
    }


    /**
     * 压缩图像
     *
     * @param originalPath     原始路径
     * @param targetMemorySize 目标内存大小
     * @throws IOException IOException
     */
    public static String compressImage(String originalPath, long targetMemorySize) throws IOException {
        File input = new File(originalPath);
        BufferedImage originalImage = ImageIO.read(input);
        // 初始的压缩比率
        double scale = 1.0;
        long currentSize = originalImage.getWidth() * originalImage.getHeight() * 3L; // 估计的原始大小（以字节为单位）

        BufferedImage resizedImage = null;
        // 循环进行压缩，直到满足目标大小的要求
        while (currentSize > targetMemorySize) {
            // 创建新的缩放后的图片
            resizedImage = new BufferedImage((int)(originalImage.getWidth() * scale), (int)(originalImage.getHeight() * scale), originalImage.getType());
            resizedImage.getGraphics().drawImage(originalImage, 0, 0, (int)(originalImage.getWidth() * scale), (int)(originalImage.getHeight() * scale), null);

            // 将压缩后的图片写入内存并获取其大小
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, getFormatName(originalPath), outputStream);
            currentSize = outputStream.size(); // 获取压缩后图片的准确大小
            System.out.println("当前图片调整后大小："+currentSize);
            // 更新压缩比率
            scale *= 0.95; // 逐渐减小压缩比率，以获得更好的压缩效果和更接近目标大小
        }

        String newPath = getNewPath(originalPath);
        ImageIO.write(resizedImage, getFormatName(originalPath), new File(newPath));
        System.out.println("图像已成功调整大小！");
        return newPath;
    }

    /**
     * 更改图像大小
     *
     * @param originalPath 原始路径
     * @param targetWidth  目标宽度
     * @param targetHeight 目标高度
     * @throws IOException IOException
     */
    private static void changeImageSize(String originalPath, int targetWidth, int targetHeight) throws IOException {
        // 读取原始图像文件
        File input = new File(originalPath);

        BufferedImage originalImage = ImageIO.read(input);
        // 创建新的BufferedImage对象，并设置其宽度、高度及颜色模型等属性
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        String newPath = getNewPath(originalPath);
        File output = new File(newPath);
        ImageIO.write(resizedImage, getFormatName(originalPath), output);
        System.out.println("图像已成功调整尺寸！");
    }

    /**
     * 调整图像大小
     *
     * @param originalImage 原始图像
     * @param width         宽度
     * @param height        身高
     * @param quality       质量
     * @return {@link BufferedImage}
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, Integer width, Integer height, double quality) {
        Image scaledImage = originalImage.getScaledInstance((int) (width * quality), (int) (height * quality), Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage((int) (width * quality), (int) (height * quality), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(scaledImage, 0, 0, width, height, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * 获取格式名称
     *
     * @param originalPath 原始路径
     * @return {@link String}
     */
    private static String getFormatName(String originalPath) {
        String formatName = originalPath.substring(originalPath.indexOf(".") + 1, originalPath.length());
        System.out.println("formatName:" + formatName);
        return formatName;
    }

    /**
     * 获取新路径
     *
     * @param originalPath 原始路径
     * @return {@link String}
     */
    private static String getNewPath(String originalPath) {
        // 保存修改后的图像到输出文件
        String fileName = originalPath.substring(originalPath.lastIndexOf("/") + 1, originalPath.indexOf("."));
        System.out.println("fileName:" + fileName);
        String newPath = originalPath.replace(fileName, fileName + 1);
        System.out.println("newPath:" + newPath);
        return newPath;
    }


}
