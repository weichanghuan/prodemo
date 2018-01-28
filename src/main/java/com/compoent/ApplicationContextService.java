package com.compoent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weichanghuan
 * @description: 自定义应用上下文
 * @reason: 存放全局变量
 * @date 2017/8/24 10:44
 * @since JDK 1.7
 */
public class ApplicationContextService {

    /**
     * 业务处理服务类集合
     */
    public final static ConcurrentHashMap<String, IProcessorService> PROCESSOR_SERVICE_MAP = new ConcurrentHashMap<String, IProcessorService>();

}
