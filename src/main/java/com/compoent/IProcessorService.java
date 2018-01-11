/**
 * Copyright (c) 2017, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 * All right reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT
 * MANAGEMENT CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD.
 */
package com.compoent;

import java.util.Map;

/**
 * @description: 统一处理接口
 * @reason: 所有功能处理接口
 * @author 危常焕
 * @date 2017/8/24 9:31
 * @since JDK 1.7
 */
public interface IProcessorService {

    <K, V, T> T execute(Map<K, V> param);

}
