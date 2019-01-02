package com.compoent;

import java.util.Map;

/**
 * @author 危常焕
 * @description: 统一处理接口
 * @reason: 所有功能处理接口
 * @date 2017/8/24 9:31
 * @since JDK 1.7
 */
public interface IProcessorService {

    <K, V, T> T execute(Map<K, V> param);

}
