package com.test.redis;

import com.utils.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJedisSpring {

    @Test
    public void testJedisClientPool() throws Exception {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext-redis.xml");
        // 从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        // 使用JedisClient对象操作redis
        jedisClient.set("testData", "testDatavalue");
        String result = jedisClient.get("jedisclient");
        System.out.println(result);
    }
}
