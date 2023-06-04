package com.local.test.utilCollection;

import org.apache.commons.io.FileUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description: 扫包生成注释的工具类
 * 此代码有缺陷，因为是截取 所以如果代码中出现 字段名+";"的字符，那么就会不准
 * 代码中我会检查出现重复就报错
 * @author: echo
 * @date: 2023/6/2
 */
public class GenerateCommentUtil {
    private final static String CLASS_COMMENT = "\n/**\n"+
                                                    "*%s\n" +
                                                    "*@author: %s/\n"+
                                                    "*@date: %s/\n"+
                                                    "*/\n";

    private final static String FIELD_COMMENT = "\n   /**\n" +
                                                   "    *%s\n" +
                                                   "    */";
    private final static List<String> GET_SET_METHODS;
    static {
        GET_SET_METHODS = new ArrayList<>();
        GET_SET_METHODS.add("equals");
        GET_SET_METHODS.add("toString");
        GET_SET_METHODS.add("hashCode");
        GET_SET_METHODS.add("canEqual");
    }


    public static void main(String[] args) throws Exception {
        String path = "/Users/echo/Documents/sCloud/LocalDemo/src/main/java/com/local/test/entity";
        scanDictionary(path);
    }

    public static void scanDictionary(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.getName().endsWith("java")) {
            readClassFile(file);
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    scanDictionary(f.getAbsolutePath());
                } else if (f.getAbsolutePath().endsWith(".java")) {
                    readClassFile(f);
                }
            }
        }
    }

    public static void readClassFile(File file) throws Exception {
        String packageClassName = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("java") + 5,
                file.getAbsolutePath().indexOf(".")).replace("/", ".");
        String className = packageClassName.substring(packageClassName.lastIndexOf(".")+1);
        Class<?> aClass = Class.forName(packageClassName);
        String content = FileUtils.readFileToString(file, "utf-8");
        String classCommentStr = generateClassComment(content,className);
        String filedCommentStr = generateFiledComment(aClass, classCommentStr);
        String methodCommentStr = generateMethodComment(aClass, filedCommentStr);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(methodCommentStr);
        bw.close();
    }

    private static String generateMethodComment(Class<?> aClass, String fileContent){
        StringBuilder sb = new StringBuilder(fileContent);
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Method[] methods = aClass.getDeclaredMethods();
        for(int i = 0;i<methods.length;i++){
            String methodName = methods[i].getName();
            if(GET_SET_METHODS.contains(methodName)) {
                continue;
            }
            String accessType = filedAccessType(methods[i].getModifiers());
            //如果声明的不为public protected private 则跳过
            if(null == accessType){
                continue;
            }
            String[] parameterNames = discoverer.getParameterNames(methods[i]);
            String methodReturnType = getMethodReturnType(methods[i]);
            String methodComment = appendMethodComment(methodName, parameterNames, methodReturnType);
            int firstSubstrIndex = CustomerStringUtil.getFirstSubstrIndex(sb.toString(), accessType, methodName, null,true);
            if(firstSubstrIndex == -1){
                continue;
            }
            String contentStr = CustomerStringUtil.insertStrByIndex(sb.toString(), firstSubstrIndex, methodComment);
            sb = new StringBuilder(contentStr);
        }
        return sb.toString();
    }

    /**
     * @param parameterNames
     * @param methodReturnType
     */
    private static String appendMethodComment(String methodName, String[] parameterNames, String methodReturnType){
        if(null == parameterNames){
            return null;
        }
        String methodComment = "\n    /**\n";
               methodComment += "     *"+methodName+"\n";
        for (String parameterName : parameterNames) {
               methodComment += "     *@param: "+ parameterName+"\n";
        }
        if(!Objects.equals(methodReturnType,"void")){
              methodComment += "     *@return: "+ methodReturnType+"\n";
        }
        methodComment += "     */";
        return methodComment;
    }

    private static String getMethodReturnType(Method method){
        String returnTypeStr = "";
        Type genericReturnType = method.getGenericReturnType();
        //获取实际返回的参数名
        String returnTypeName = genericReturnType.getTypeName();
        //判断是否是参数化类型
        if(genericReturnType instanceof ParameterizedType) {
            return returnTypeName;
        }else {
            //不是参数化类型,直接获取返回值类型
            Class<?> returnType = method.getReturnType();
            //获取返回值类型的类名
            returnTypeStr = returnType.getName();
        }
        return returnTypeStr;
    }

    private static String generateClassComment(String content,String className){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if(content.contains("@author")){
            return content;
        }
        int annotationIndex = content.indexOf("@");
        int classIndex = content.indexOf("class");
        int aPublicIndex = content.indexOf("public");
        if(annotationIndex < classIndex){
            aPublicIndex = annotationIndex;
        }
        String classComment = String.format(CLASS_COMMENT,className, "Echo", sdf.format(new Date()));
        return CustomerStringUtil.insertStrByIndex(content, aPublicIndex, classComment);
    }

    private static String generateFiledComment(Class<?> aClass, String fileContent) throws Exception {
        StringBuilder sb = new StringBuilder(fileContent);
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            String curFieldName = declaredField.getName();
            String accessType = filedAccessType(declaredField.getModifiers());
            //如果未声明则不给加注释
            if(null == accessType){
                continue;
            }
            String comment = String.format(FIELD_COMMENT, curFieldName);
            //判断属性是否为常量
            if(Modifier.isFinal(declaredField.getModifiers())){
                String nextStr = fileContent.substring(fileContent.indexOf(curFieldName));
                curFieldName = nextStr.substring(nextStr.indexOf(curFieldName), nextStr.indexOf(";"));
            }else {
                if(!fileContent.contains(curFieldName+";")){
                    throw new Exception("请格式化代码后再操作");
                }
                if(fileContent.indexOf(curFieldName+";") != fileContent.lastIndexOf(curFieldName+";")){
                    throw new Exception(curFieldName+";在代码中重复，请检查后再操作！");
                }
                GET_SET_METHODS.add("set"+CustomerStringUtil.getDefaultGetSetMethodName(curFieldName));
                GET_SET_METHODS.add("get"+CustomerStringUtil.getDefaultGetSetMethodName(curFieldName));
            }
            int firstSubstrIndex;
            if (i == 0) {
                firstSubstrIndex = CustomerStringUtil.getFirstSubstrIndex(sb.toString(), accessType, curFieldName+";", null,false);
            } else {
                String preFiledName = declaredFields[i - 1].getName();
                if(Modifier.isFinal(declaredFields[i - 1].getModifiers())){
                    String nextStr = fileContent.substring(fileContent.indexOf(preFiledName));
                    preFiledName = nextStr.substring(nextStr.indexOf(preFiledName), nextStr.indexOf(";"));
                }
                firstSubstrIndex = CustomerStringUtil.getFirstSubstrIndex(sb.toString(), accessType, curFieldName+";", preFiledName + ";",false);
            }
            if(firstSubstrIndex == -1){
                continue;
            }
            String contentSb = CustomerStringUtil.insertStrByIndex(sb.toString(), firstSubstrIndex, comment);
            sb = new StringBuilder(contentSb);
        }
        return sb.toString();
    }


    public static String filedAccessType(int modifiers) {
        if (Modifier.isPrivate(modifiers)) {
            return "private";
        } else if (Modifier.isProtected(modifiers)) {
            return "protected";
        } else if (Modifier.isPublic(modifiers)) {
            return "public";
        }
        return null;
    }
}
