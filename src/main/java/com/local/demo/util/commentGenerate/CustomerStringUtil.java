package com.local.demo.util.commentGenerate;


/**
 * @description: 自定义字符串工具类
 * @author: echo
 * @date: 2023/6/2
 */
public class CustomerStringUtil {

    public static int getFirstSubstrIndex(String content, String strStart, String strEnd, String preFiledName,Boolean isMethod) {
        if (!content.contains(strStart) || !content.contains(strEnd)) {
            return -1;
        }
        if(isMethod){
            String substring = content.substring(0, content.indexOf(strEnd) + strEnd.length());
            if(substring.substring(substring.lastIndexOf(";"),substring.indexOf(strEnd)).contains("/**")){
                return -1;
            }
            String methodStr = substring.substring(substring.lastIndexOf(strStart), substring.indexOf(strEnd) + strEnd.length());
            int spaceTab = getFirstSpaceLength(substring, methodStr);
            return content.indexOf(methodStr) - spaceTab;
        }else if (null == preFiledName) {
            String substring = content.substring(content.indexOf(strStart), content.indexOf(strEnd) + strEnd.length());
            String firstSubstr = content.substring(content.indexOf("{"),content.indexOf(strEnd));
            if(firstSubstr.contains("/**")){
                return -1;
            }
            int spaceTab = getFirstSpaceLength(firstSubstr, strStart);
            return content.indexOf(substring) - spaceTab;
        } else {
            String substring = content.substring(content.indexOf(preFiledName), content.indexOf(strEnd) + strEnd.length());
            if(substring.contains("{") && substring.indexOf("}") < substring.indexOf(strEnd)){
                int index = content.indexOf(preFiledName) + substring.indexOf("}");
                substring = content.substring(index,content.indexOf(strEnd) + strEnd.length());
                substring = substring.substring(substring.lastIndexOf("}"));
            }
            if(substring.contains("/**")){
                return -1;
            }
            int spaceTab = getFirstSpaceLength(substring, strStart);
            String curFieldSubstring = substring.substring(substring.indexOf(strStart), substring.indexOf(strEnd) + strEnd.length());
            return content.indexOf(curFieldSubstring)-spaceTab;
        }
    }

    public static void main(String[] args) {
        System.out.println("1234{6789{".indexOf("{"));

    }


    /**
     * 获取content 中对应的key 前面连续的空格数
     * @param content
     * @param key
     * @return
     */
    public static int getFirstSpaceLength(String content, String key) {
        int index = content.indexOf(key);
        String substring = content.substring(0, index);
        return index - substring.lastIndexOf("\n");
    }

    /**
     * 获取默认的方法名
     * @param fieldName
     * @return
     */
    public static String getDefaultGetSetMethodName(String fieldName){
        String upperCase = fieldName.toUpperCase();
        return upperCase.toCharArray()[0] + fieldName.substring(1);
    }

    /**
     * 在文本content的下标index
     * 前添加一段字符insertKey
     *
     * @param content
     * @param index
     * @param insertStr
     * @return
     */
    public static String insertStrByIndex(String content, int index, String insertStr) {
        StringBuilder sb = new StringBuilder(content);
        StringBuilder newSb = sb.insert(index, insertStr);
        System.out.println(newSb.toString());
       return newSb.toString();
    }

    /**
     * 在文本content的第一个key字符
     * 前添加一段字符insertKey
     *
     * @param content
     * @param keyStr
     * @param insertStr
     * @return
     */
    public static String insertStrBeforeKey(String content, String keyStr, String insertStr) {
        if (!content.contains(keyStr)) {
            return content;
        }
        return insertStrByIndex(content, content.indexOf(keyStr), insertStr);
    }



    /**
     * 在文本content的第一个key字符
     * 后添加一段字符insertKey
     *
     * @param content
     * @param keyStr
     * @param insertStr
     * @return
     */
    public static String insertStrAfterKey(String content, String keyStr, String insertStr) {
        if (!content.contains(keyStr)) {
            return content;
        }
        return insertStrByIndex(content, content.indexOf(keyStr) + keyStr.length(), insertStr);
    }
}
