package com.compoent;

import com.po.SysRoleResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wch
 * @Date 2018/6/5
 */
@Configuration
public class TestBean {

    @Bean
    public SysRoleResource getSysRoleResource() {
        SysRoleResource sysRoleResource = new SysRoleResource();
        sysRoleResource.setId(1);
        sysRoleResource.setResourceId(2);
        sysRoleResource.setRoleId(3);
        return sysRoleResource;
    }


    @Bean
    public String getString(SysRoleResource sysRoleResource) {
    	System.out.println(sysRoleResource.getId());
        return "2";
    }
}
