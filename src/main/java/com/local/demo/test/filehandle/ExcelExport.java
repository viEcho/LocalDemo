package com.local.demo.test.filehandle;

import com.google.common.collect.Lists;
import com.local.demo.entity.UserInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * excel导出
 *
 * @author echo
 * @date 2023/09/25
 */
public class ExcelExport {
    /**
     * main 方法
     *
     * @param args args
     * @throws Exception 例外
     */
    public static void main(String[] args) throws Exception{
        Long start = System.currentTimeMillis();
        List<UserInfo> userInfoList =new ArrayList<>();
        for(int i=1;i<=10000;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(Long.getLong(Integer.toString(i)))
                    .setName("echo"+i)
                    .setPassword(UUID.randomUUID().toString());
            userInfoList.add(userInfo);
        }
        String fileUrl = "/Users/echo/Desktop/testExcel.xlsx";
        FileInputStream fs;
        FileOutputStream os = null;
        try {
            File excel = new File(fileUrl);
            fs=new FileInputStream(excel);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            SXSSFWorkbook wb = new SXSSFWorkbook(workbook,1000);//内存中保留 1000 条数据，以免内存溢出，其余写入 硬盘
            //获得该工作区的第一个sheet 如果已有模板 和模板名字一致即可
            //Sheet sheet1 = wb.createSheet("com.local.demo.test.test");
            Sheet sheet1 = wb.getSheetAt(0);
            int excelRow = 1;
            List<String> columnList = new ArrayList<>();
            columnList.add("id");
            columnList.add(" 姓名");
            columnList.add("密码");
            //标题行
            Row titleRow = (Row) sheet1.createRow(excelRow++);
            for (int i = 0; i < columnList.size(); i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(columnList. get(i));
            }
            //明细行
            List<List<UserInfo>> reParam = Lists.partition(userInfoList, 1000);
            for (int x = 0;x<reParam.size();x++) {
                List<UserInfo> userInfos = reParam.get(x);
                for (int j = 0; j < userInfos.size(); j++) {
                    Row contentRow = sheet1.createRow(excelRow++);
                    Cell cell0 = contentRow.createCell(0);
                    cell0.setCellValue(userInfos.get(j).getPassword());

                    Cell cell1 = contentRow.createCell(1);
                    cell1.setCellValue(userInfos.get(j).getName());
                    Cell cell2 = contentRow.createCell(2);
                    cell2.setCellValue(userInfos.get(j).getPassword());
                }
            }
            os = new FileOutputStream(fileUrl);
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } // 关闭输出流
        }
        System.out.println("导出耗时："+(System.currentTimeMillis()-start));
    }

}
