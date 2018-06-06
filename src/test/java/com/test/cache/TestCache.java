package com.test.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wch
 * @Date 2018/6/3
 */
public class TestCache {

    private static final Logger logger = LoggerFactory.getLogger(TestCache.class);

    @Test
    public void testCache() {
        Map<String, String> map = new HashMap<>();
        for (long i = 0; i < 10000; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }

        testSetRedis(map);

        testSetEhcache(map);

        logger.info("test is end");
    }


    private void testSetRedis(Map<String, String> testData) {
        // 创建一个jedis对象，需要指定服务的ip和端口号
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 直接操作数据库
        long start = System.currentTimeMillis();
        logger.info("test redis set time is start={}", start);
        for (Map.Entry<String, String> entry : testData.entrySet()) {
            jedis.set(entry.getKey(), entry.getValue());
        }
        long end = System.currentTimeMillis();
        logger.info("test redis set time is end={}", end);
        //String result = jedis.get("jedis-key");
        // System.out.println(result);
        // 关闭jedis
        jedis.close();
        logger.info("test redis set time is {}", end - start);
        return;
    }


    private void testSetEhcache(Map<String, String> testData) {
        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:test-ehcache.xml");
        // 从容器中获得CacheManager对象
        CacheManager cacheManager = applicationContext.getBean(CacheManager.class);
        // 创建一个缓存管理器
        CacheManager singletonManager = CacheManager.create();
        Cache memoryOnlyCache = new Cache("testCache", 5000, false,
                false, 5,
                2);
        //在内存管理器中添加缓存实例
        singletonManager.addCache(memoryOnlyCache);
        // 在缓存管理器中获取一个缓存实例
        Cache cache = singletonManager.getCache("testCache");
        // 使用获取到的缓存实例,添加数据
        long start = System.currentTimeMillis();
        logger.info("test ehcache set time is start={}", start);
        for (Map.Entry<String, String> entry : testData.entrySet()) {
            Element element = new Element(entry.getKey(), entry.getValue());
            cache.put(element);
        }
        long end = System.currentTimeMillis();
        logger.info("test ehcache set time is end={}", end);
        logger.info("test ehcache set time is {}", end - start);
        int elementsInMemory = cache.getSize();// 获取缓存个数
        System.out.println("缓存个数=======" + elementsInMemory);
        // Object obj = element.getObjectValue();//获取对象值
        // cache.remove("key1");//删除缓存
        Cache cache2 = singletonManager.getCache("testCache");// 获取缓存实例
        Element element2 = cache2.get("1");
        System.out.println("value=====" + element2.getValue());// 获取缓存值
        singletonManager.shutdown();
    }


}