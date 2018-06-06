package com.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Wch
 * @Date 2018/6/6
 */
public class TestDataSouces {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "test_dataSources.xml");

    public static void main(String[] args) {
        DataSouces d = (DataSouces) context.getBean("dataSources");
        System.out.println(d);
        System.out.println(d.getId());
        System.out.println(d.getPassWord());
    }
}
