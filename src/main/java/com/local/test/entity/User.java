package com.local.test.entity;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

/**
*User
*@author: Echo/
*@date: 2023/06/04/
*/
@Data
public class User {

   /**
    *AA
    */
    private final static String AA = "AAA";

   /**
    *id
    */
    private String id;

   /**
    *name
    */
    private String name;

   /**
    *address
    */
    private String address;

    /**
     *testA
     */
    public void testA() {

    }


   /**
    *sex
    */
    private int sex;

    /**
     *testB
     *@param: address
     *@param: phone1
     *@return: java.lang.String
     */
    private String testB(String address, String phone1) {
        return "地址："+address+"电话号码："+phone1;
    }


    /**
     *
     * @param address
     * @param phone
     * @param name
     * @return
     */
    protected List<String> getAllList(String address,String phone,String name) {
        return Arrays.asList(address,phone,name);
    }

   /**
    *phone
    */
    private int phone;
}
