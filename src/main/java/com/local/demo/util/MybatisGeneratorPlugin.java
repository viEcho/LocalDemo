package com.local.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * mybatis生成器插件
 *
 * @author echo
 * @date 2024/03/08
 */
@Slf4j
public class MybatisGeneratorPlugin extends PluginAdapter {
    /**
     * 当前git用户名
     */
    private static String CURRENT_GIT_USER_NAME = "Your name";

    /**
     * main 方法
     *
     * @param args args
     */
    public static void main(String[] args) {
        args = new String[] { "-configfile", System.getProperty("user.dir")+"/src/main/resources/mybatis-generator.xml", "-overwrite" };
        ShellRunner.main(args);
    }

    static{
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("git", "config", "--global", "user.name");
        try {
            Process process = processBuilder.start();
            // 读取标准输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            CURRENT_GIT_USER_NAME = reader.readLine();
        } catch (IOException e) {
            log.error("MybatisGeneratorPlugin 获取当前git用户名异常");
        }
    }


    /**
     * 验证
     *
     * @param list 列表
     * @return boolean
     */
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 实体类生成时添加注释
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return boolean
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 添加import
        topLevelClass.addImportedType("lombok.Data");
        // 添加注解
        topLevelClass.addAnnotation("@Data");
        if (StringUtility.stringHasValue(introspectedTable.getRemarks())) {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
            topLevelClass.addJavaDocLine(" *");
            topLevelClass.addJavaDocLine(" * @author " + CURRENT_GIT_USER_NAME);
            topLevelClass.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            topLevelClass.addJavaDocLine(" */");
        } else {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * TODO 请添加类注释");
            topLevelClass.addJavaDocLine(" *");
            topLevelClass.addJavaDocLine(" * @author " + CURRENT_GIT_USER_NAME);
            topLevelClass.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            topLevelClass.addJavaDocLine(" */");
        }
        return true;

    }

    /**
     * 字段生成时添加注释
     *
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return boolean
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }
        return true;
    }

    /**
     * mapper生成时添加注释
     *
     * @param var1 var1
     * @param var2 var2
     * @return boolean
     */
    @Override
    public boolean clientGenerated(Interface var1, IntrospectedTable var2){
        var1.addJavaDocLine("import org.apache.ibatis.annotations.Mapper;");
        var1.addJavaDocLine("\n");
        var1.addJavaDocLine("/**");
        var1.addJavaDocLine(" * TODO 请添加类注释");
        var1.addJavaDocLine(" *");
        var1.addJavaDocLine(" * @author "+CURRENT_GIT_USER_NAME);
        var1.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        var1.addJavaDocLine(" */");

        var1.addAnnotation("@Mapper");
        return true;
    }

    /**
     * 去掉生成getter方法
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return boolean
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 去掉生成setter方法
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return boolean
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**=====以下方法是去掉mapper及xml中不必要的DML操作方法，使得生成的代码保持干净=====*/
    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method var1, Interface var2, IntrospectedTable var3){
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method var1, Interface var2, IntrospectedTable var3){
        return false;
    }

    @Override
    public boolean clientUpdateAllColumnsMethodGenerated(Method var1, Interface var2, IntrospectedTable var3){
        return false;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement var1, IntrospectedTable var2){
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement var1, IntrospectedTable var2){
        return false;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method var1, Interface var2, IntrospectedTable var3){
        return false;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement var1, IntrospectedTable var2){
        return false;
    }
}
