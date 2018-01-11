package com.test;

import com.compoent.SysRoleResourceEhcacheService;
import com.po.SysRoleResource;
import com.service.SysRoleResourceService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDemo extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysRoleResourceEhcacheService sysRoleResourceEhcacheService;
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    // 测试专用
    @Test
    public void selectByPrimaryKey() {
        logger.debug("测试方法开始");
        SysRoleResource selectByPrimaryKey = sysRoleResourceService.selectByPrimaryKey(8);
        logger.debug("测试方法结束");
    }

    @Test
    public void insert() {
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setId(555666);
        sysRoleResource.setResourceId(555666);
        sysRoleResource.setRoleId(555666);
        int insert = sysRoleResourceService.insert(sysRoleResource);
        logger.debug(insert + "");

    }

    @Test
    public void selectByPrimaryKey2() {
        SysRoleResource selectByPrimaryKey = sysRoleResourceEhcacheService.selectByPrimaryKey(8);
        System.err.println(selectByPrimaryKey.getRoleId());
        logger.debug(selectByPrimaryKey.getRoleId() + "" + "222");
        logger.error(selectByPrimaryKey.getRoleId() + "" + "222");

    }

    @Test
    public void selectByAny() {
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setId(8);
        Long start = System.currentTimeMillis();
        SysRoleResource selectByPrimaryKey = sysRoleResourceEhcacheService.selectByPrimaryKey(8);
        System.out.println("查询所用时间" + (System.currentTimeMillis() - start));
        System.out.println("第一次查询结束");
        Long start1 = System.currentTimeMillis();
        SysRoleResource selectByPrimaryKey2 = sysRoleResourceEhcacheService.selectByPrimaryKey(8);
        System.out.println("查询所用时间" + (System.currentTimeMillis() - start1));
        System.out.println("第二次查询结束");
    };

}
