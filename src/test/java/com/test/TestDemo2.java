package com.test;

import com.po.SysRoleResource;
import com.service.SysRoleResourceService;
import com.service.impl.SysRoleResourceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.dbunit.annotation.DataSet;

public class TestDemo2 extends BaseUnitilsTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SysRoleResourceService sysRoleResourceService;

    @Before
    public void setUp() {
        sysRoleResourceService = (SysRoleResourceService) ctx.getBean(SysRoleResourceServiceImpl.class);
    }

    @Test
    @DataSet(value = { "excel\\demo.xls" })
    // 通过Unitils提供的@DataSet注解从当前测试类this.class所在的目录寻找支持DbUnit的数据集文件并进行加载。
    // 执行测试逻辑之前，会把加载的数据集先持久化到测试数据库中，
    // @ExpectedDataSet(value = {
    // "com\\odianyun\\demo\\dao\\WarehouseDAOTestdemo.xls" })
    // 是在单元测试之后进行数据比对
    // 从当前测试类this.class所在的目录寻找支持DbUnit的验证数据集文件并进行加载，
    // 之后验证数据集里的数据和数据库中的数据是否一致
    public void selectByPrimaryKey() {
        SysRoleResource selectByPrimaryKey = sysRoleResourceService.selectByPrimaryKey(1);
        System.err.println(selectByPrimaryKey.getRoleId());
        logger.debug(selectByPrimaryKey.getRoleId() + "");

    };

}
