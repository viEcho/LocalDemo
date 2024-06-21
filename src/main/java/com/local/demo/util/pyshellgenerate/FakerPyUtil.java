package com.local.demo.util.pyshellgenerate;

import cn.hutool.core.util.StrUtil;
import com.local.demo.aop.Faker;
import com.local.demo.entity.UserInfo;
import com.local.demo.enums.FakerTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class FakerPyUtil {

    private final static String HOST = "127.0.0.1";
    private final static String DB = "local_demo";
    private final static int PORT = 3306;
    private final static String USER = "root";
    private final static String PWD = "123456";


    public static void main(String[] args) {
        // py地址
        String baseFilePath = "/Users/echo/PycharmProjects/pythonProject/mockSqlPy/";
        List<Class<?>> classes = Arrays.asList(UserInfo.class);
        generatePyAccordEntity(classes, true, 10, baseFilePath);
    }

    /**
     * 根据实体生成PY脚本
     *
     * @param clazzList           clazz列表
     * @param needTruncateOldData 是否需要清空旧数据
     * @param generateNum         生成num
     * @param baseFilePath        基本文件路径
     */
    public static void generatePyAccordEntity(List<Class<?>> clazzList, Boolean needTruncateOldData, int generateNum, String baseFilePath) {
        if (CollectionUtils.isEmpty(clazzList)) {
            return;
        }
        for (Class<?> aClass : clazzList) {
            try {
                log.info("===>> 开始生成{}实体类的造数脚本", aClass);
                String className = aClass.getName().substring(aClass.getName().lastIndexOf(".") + 1);
                String tableName = convertToUnderlineName(className);
                log.info("实体类对应的表：{}", tableName);

                String filePath = baseFilePath + tableName + ".py";
                log.info("输出位置：{}", filePath);
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write("# This is a Python script\n");
                fileWriter.write("import faker \n");
                fileWriter.write("import random \n");
                fileWriter.write("import pymysql \n");
                fileWriter.write("from datetime import datetime \n\n");

                fileWriter.write("connection = pymysql.connect(host='"+HOST+"', port="+ PORT +", user='"+USER +"', password='"+PWD +"', db='"+DB +"')\n\n");
                fileWriter.write("print (\"数据库连接成功\")\n\n");
                fileWriter.write("faker = faker.Faker(\"zh_CN\")\n\n\n");

                fileWriter.write("def generate_table_name():\n");
                fileWriter.write("    table_name1 = faker.pystr(min_chars=2,max_chars=3)\n");
                fileWriter.write("    table_name2 = faker.pystr(min_chars=2,max_chars=3)\n");
                fileWriter.write("    words =[table_name1, table_name2]\n");
                fileWriter.write("    return '_'. join(words).lower()\n\n\n");
                fileWriter.write("def generate_field_name():\n");
                fileWriter.write("    field_name1 = faker.pystr(min_chars=3,max_chars=5)\n");
                fileWriter.write("    field_name2 = faker.pystr(min_chars=3,max_chars=5)\n");
                fileWriter.write("    words =[field_name1, field_name2]\n");
                fileWriter.write("    return '_'. join(words).lower()\n\n\n");

                fileWriter.write("def create_data():\n");
                fileWriter.write("    try :\n");
                fileWriter.write("        connection_cursor = connection.cursor()\n");
                if (needTruncateOldData) {
                    fileWriter.write("        connection_cursor.execute(\"TRUNCATE TABLE " + tableName + "\")\n");
                    fileWriter.write("        connection.commit()\n");
                }
                fileWriter.write("        for _ in range(" + generateNum + "):\n");
                List<StringBuilder> buildSqlFieldList = buildInsertSqField(fileWriter, aClass);
                fileWriter.write("            " + "execute_sql = f\"\"\"INSERT INTO " + tableName + "(" + buildSqlFieldList.get(0) + ") values (" + buildSqlFieldList.get(1) + ");\"\"\"\n\n");
                fileWriter.write("            " + "print(execute_sql)\n\n");
                fileWriter.write("            " + "connection_cursor.execute(execute_sql)\n");
                fileWriter.write("            " + "connection.commit()\n");
                fileWriter.write("    finally:\n");
                fileWriter.write("        connection.close()\n\n\n");
                fileWriter.write("if __name__ == \"__main__\":\n");
                fileWriter.write("    create_data()\n");
                fileWriter.close();
                log.info("====>> 生成{}实体的造数脚本完毕", aClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * 生成插入sql字段
     *
     * @param fileWriter 文件写入程序
     * @param aClass     类
     * @return {@link List}<{@link StringBuilder}>
     * @throws IOException IOException
     */
    private static List<StringBuilder> buildInsertSqField(FileWriter fileWriter, Class<?> aClass) throws IOException {
        List<StringBuilder> returnList = new ArrayList<>();
        List<Field> allFields = getAllFields(aClass);
        StringBuilder beforeSqlStrBuilder = new StringBuilder();
        StringBuilder lastSqlStrBuilder = new StringBuilder();
        long ignoreCount = allFields.stream().filter(f -> Objects.equals(f.getAnnotation(Faker.class).fakerType(), FakerTypeEnum.IGNORE) &&
                StrUtil.isBlank(f.getAnnotation(Faker.class).customVal())).count();
        int executedIndex = 0;
        for (int i = 0; i < allFields.size(); i++) {
            Field field = allFields.get(i);
            if (field.isAnnotationPresent(Faker.class)) {
                Faker annotation = field.getAnnotation(Faker.class);
                //自增和有默认值的字段不处理
                if (StrUtil.isBlank(annotation.customVal()) && Objects.equals(field.getAnnotation(Faker.class).fakerType(), FakerTypeEnum.IGNORE)) {
                    continue;
                }
                String fieldName = field.getName();
                String tableFieldName = convertToUnderlineName(fieldName);
                FakerTypeEnum fakerTypeEnum = annotation.fakerType();
                if (StrUtil.isNotBlank(annotation.customVal())) {
                    fileWriter.write("            " + tableFieldName + " = '" + annotation.customVal() + "'\n");
                } else {
                    fileWriter.write("            " + tableFieldName + " = " + fakerTypeEnum.getFakerVal() + "\n");
                }
                if (executedIndex == allFields.size() - ignoreCount - 1) {
                    beforeSqlStrBuilder.append(tableFieldName);
                    lastSqlStrBuilder.append("'{").append(tableFieldName).append("}'");
                } else {
                    beforeSqlStrBuilder.append(tableFieldName).append(",");
                    lastSqlStrBuilder.append("'{").append(tableFieldName).append("}',");
                }
                executedIndex++;
            }
        }
        returnList.add(beforeSqlStrBuilder);
        returnList.add(lastSqlStrBuilder);
        return returnList;
    }


    /**
     * 获取所有字段
     *
     * @param type 类型
     * @return {@link List}<{@link Field}>
     */
    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            Field[] declaredFields = c.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                fields.add(declaredField);
            }
        }
        return fields;
    }


    /**
     * 转换为下划线名称
     *
     * @param className 类名
     * @return {@link String}
     */
    private static String convertToUnderlineName(String className) {
        StringBuilder tableName = new StringBuilder();
        char[] charArray = className.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i == 0) {
                tableName.append(Character.toLowerCase(charArray[0]));
                continue;
            }
            if (Character.isUpperCase(charArray[i])) {
                tableName.append('_').append(Character.toLowerCase(charArray[i]));
            } else {
                tableName.append(Character.toLowerCase(charArray[i]));
            }
        }
        return tableName.toString();
    }

}
