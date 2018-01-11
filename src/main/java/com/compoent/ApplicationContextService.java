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
