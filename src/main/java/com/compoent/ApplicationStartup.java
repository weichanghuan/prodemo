package com.compoent;

import com.controller.ProcessorControllerAnnotation;
import com.po.SysRoleResource;
import com.service.SysRoleResourceService;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    private static Integer count = 0;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        count++;
        System.out.println("调用次数" + count);
        // 1)ContextRefreshedEvent：当ApplicationContext初始化或者刷新时触发该事件。
        // 2)ContextClosedEvent：当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。
        // 3)RequestHandleEvent：在Web应用中，当一个http请求（request）结束触发该事件。
        // 4)ContextStartedEvent：Spring2.5新增的事件，当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件。
        // 5)ContextStopedEvent：Spring2.5新增的事件，当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。
        if (event instanceof ContextClosedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else if (event instanceof ContextRefreshedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
            logger.debug("------初始化执行----");
            List<SysRoleResource> selectByAny = sysRoleResourceService.selectByAny(new SysRoleResource());
            // 创建一个缓存管理器
            CacheManager singletonManager = CacheManager.create();
            // 建立一个缓存实例
            // Cache memoryOnlyCache = new Cache("testCache", 5000, false,
            // false, 5,
            // 2);
            // 在内存管理器中添加缓存实例
            // singletonManager.addCache(memoryOnlyCache);
            // 在缓存管理器中获取一个缓存实例
            // Cache cache = singletonManager.getCache("testCache");
            Cache cache = singletonManager.getCache("testInfo");
            // 使用获取到的缓存实例
            for (SysRoleResource sysRoleResource : selectByAny) {
                Element element = new Element(sysRoleResource.getId(), sysRoleResource);
                cache.put(element);// 添加缓存值
            }

            int elementsInMemory = cache.getSize();// 获取缓存个数
            System.out.println("缓存个数=======" + elementsInMemory);

            // Object obj = element.getObjectValue();//获取对象值
            // cache.remove("key1");//删除缓存

            // Cache cache2 = singletonManager.getCache("testCache");// 获取缓存实例
            // Element element2 = cache2.get("key1");
            // System.out.println("value=====" + element2.getValue());// 获取缓存值
            // singletonManager.shutdown();
            /*
             * for (int j = 0; j < 100; j++) { i++; System.out.println(i); } //
             * 获取上下文 ApplicationContext context = ((ContextClosedEvent)
             * event).getApplicationContext(); // 获取所有beanNames String[]
             * beanNames = context.getBeanNamesForType(Object.class); for
             * (String string : beanNames) { System.out.println(string); }
             */
            // 第二步：
            logger.info("开始执行业务服务注解监听！");
            try {
                // 获取上下文
                ApplicationContext context = ((ContextRefreshedEvent) event).getApplicationContext();
                // 获取所有beanNames
                String[] beanNames = context.getBeanNamesForType(Object.class);
                for (String beanName : beanNames) {
                    ProcessorControllerAnnotation processorService = context.findAnnotationOnBean(beanName, ProcessorControllerAnnotation.class);
                    // 判断该类是否含有ProcessorServiceAnnotation注解
                    if (processorService != null) {
                        ApplicationContextService.PROCESSOR_SERVICE_MAP.put(processorService.value(), (IProcessorService) context.getBean(beanName));
                    }
                }
            } catch (Exception e) {
                logger.error("执行业务服务注解监听，异常:{}", e);
            }
        } else if (event instanceof ContextStartedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else if (event instanceof ContextStoppedEvent) {
            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
        } else {
            System.out.println("有其它事件发生:" + event.getClass().getName());
        }

    }

}
