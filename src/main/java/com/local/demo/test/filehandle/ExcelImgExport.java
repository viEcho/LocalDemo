package com.local.demo.test.filehandle;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * excel img导出
 *
 * @author echo
 * @date 2023/09/25
 */
public class ExcelImgExport {

    /**
     * main 方法
     *
     * @param args args
     */
    public static void main(String[] args) {
        exportExcelOne();
    }


    /**
     * 1.竖向排列
     */
    public static void exportExcelOne() {
        String[] imgs = { "/Users/echo/Desktop/WechatIMG170.jpg"};
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建工作簿
        HSSFSheet sheet = workBook.createSheet();// 创建工作表
        sheet.setColumnWidth(0, 4800);// 列宽
        BufferedImage bufferedImage = null;
        HSSFPatriarch hSSFPatriarch = sheet.createDrawingPatriarch();// 画图管理器
        try {
            for (int i = 0; i < 3; i++) {
                HSSFRow imgRow = sheet.createRow(i);// 行
                File file = new File(imgs[0]);
                imgRow.setHeight((short) 1000);// 设置高度
                bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
                HSSFClientAnchor hSSFClientAnchor = new HSSFClientAnchor(
                        20, /* 开始点 x坐标,范围 0-1023 */
                        10, /* 开始点y坐标范.围 0-255 */
                        1003, /* 结束点x坐标,范围 0-1023 */
                        245, /* 结束点y坐标,范围 0-255 */
                        (short) 0, /* 开始单元格列号,范围0-255 */
                        i, /* 开始单元格行号,范围0-255 * 256 */
                        (short) 0, /* 结束单元格列号,范围0-255 */
                        i/* 结束单元格行号,范围0-255 * 256 */
                );
                hSSFPatriarch.createPicture(hSSFClientAnchor,
                        workBook.addPicture(byteArrayOutputStream.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
            }
            FileOutputStream outputStream = new FileOutputStream("/Users/echo/Desktop/" + new Date().getTime() + ".xls");
            workBook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


