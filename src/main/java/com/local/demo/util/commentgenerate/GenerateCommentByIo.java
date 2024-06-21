package com.local.demo.util.commentgenerate;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description: 根据io 流读取java 文件添加注释
 * @author: echo
 * @date: 2023/6/8
 */
public class GenerateCommentByIo {

    private final static String CLASS_COMMENT = "\n/**\n" +
            "*%s\n" +
            "* @author: %s/\n" +
            "* @date: %s/\n" +
            "*/\n";

    private final static String FIELD_COMMENT = "\n   /**\n" +
            "    * %s\n" +
            "    */";
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) throws Exception {
        String path = "/Users/echo/Documents/sCloud/LocalDemo/src/main/java/com/local/com.local.demo.test.test/entity";
        scanDictionary(path);
    }



    /**
     * @description: 文件扫描
     * @author: echo
     * @date: 2023/6/8
     * @param: path
     * @return: void
     */
    public static void scanDictionary(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.getName().endsWith("java")) {
            readJavaFile(file);
        }
        if (!file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                scanDictionary(f.getAbsolutePath());
            } else if (f.getAbsolutePath().endsWith(".java")) {
                readJavaFile(f);
            }
        }
    }

    private static void readJavaFile(File file) throws IOException {
        String packageClassName = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("java") + 5,
                file.getAbsolutePath().indexOf(".")).replace("/", ".");
        String className = packageClassName.substring(packageClassName.lastIndexOf(".") + 1);
        String classComment = String.format(CLASS_COMMENT, className, "Echo", sdf.format(new Date()));
        String fileContent = FileUtils.readFileToString(file, "utf-8");
        List<String> strings = FileUtils.readLines(file, "utf-8");
        StringBuilder sb = new StringBuilder();
        Integer classType = null;
        Integer[] methodIndexArray = new Integer[2];
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i)).append("\r\n");
            if (classType == null) {
                continue;
            }
            if (methodIndexArray[0] != null && i > methodIndexArray[0] && i < methodIndexArray[1]) {
                continue;
            }
            CurStrInfo curStrInfo = dynamicGenerateNewContent(strings, fileContent, sb ,i, classType, methodIndexArray);
            classType = curStrInfo.getClassType();
            methodIndexArray[0] = curStrInfo.getMethodStartRow();
            methodIndexArray[1] = curStrInfo.getMethodEndRow();
        }

        if (fileContent.equals(sb.toString())) {
            return;
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString());
        bw.close();
    }

    private static CurStrInfo dynamicGenerateNewContent(List<String> strings, String initContent, StringBuilder sb, int rowI, Integer classType, Integer[] methodIndex) {
        String curStr = strings.get(rowI);
        CurStrInfo curStrInfo = new CurStrInfo();
        curStrInfo.setKeyStr(curStr);
        curStrInfo.setClassType(classType);
        if (classType == null) {
            judgeClassType(strings.get(rowI), curStrInfo);
        }
        if (curStr.contains("final") && (curStr.contains(";") || curStr.contains("="))){
            curStrInfo.setStrType(1);
        } else if(curStr.contains(";")){

        } else if(1==1){

        }
        return curStrInfo;
    }

    @Data
    static class CurStrInfo {
        private String keyStr;
        private Integer needInsertIndex;
        private Integer classType;//1.接口 2.类 3.枚举
        private Integer strType;//1.常量 2.普通全局变量 3.注入变量 4.属性 5.方法 6.静态内部类
        private Integer methodStartRow;//方法开始行
        private Integer methodEndRow;//方法结束行

        public CurStrInfo() {

        }
    }
    private static void judgeClassType(String str, CurStrInfo curStrInfo) {
        if (str.contains("interface") && str.contains("{")) {
            curStrInfo.setClassType(1);
        } else if (str.contains("class") && str.contains("{")) {
            curStrInfo.setClassType(2);
        } else if (str.contains("enum") && str.contains("{")) {
            curStrInfo.setClassType(3);
        }
    }


}
