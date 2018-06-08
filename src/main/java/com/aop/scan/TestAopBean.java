package com.aop.scan;

import com.aop.anno.AddLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
@Service
public class TestAopBean {

    private static final Logger logger = LoggerFactory.getLogger(TestAopBean.class);


    @AddLog
    public String excute(String param1, String param2) {

        logger.info("param1 is {}", param1);
        logger.info("param2 is {}", param2);
        logger.info("function is end");

        return "end";
    }

}
