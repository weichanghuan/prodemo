package com.compoent;

import java.util.Map;

/**
 * @author weichanghuan
 * @description: 统一处理接口实现
 * @reason: 实现业务接口以及业务公共处理
 * @date 2017/8/24 9:34
 * @since JDK 1.7
 */
public class ProcessorServiceImpl implements IProcessorService {

    @Override
    public <K, V, T> T execute(Map<K, V> param) {
        return null;
    }

}
