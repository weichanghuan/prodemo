package com.aop;

import com.aop.scan.TestAopBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
public class TestAopScan {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "test_aop.xml");

    public static void main(String[] args) {
        TestAopBean d = (TestAopBean) context.getBean("testAopBean");
        d.excute("param1", "param2");
    }
}
