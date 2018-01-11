package com.controller;

import com.compoent.SysRoleResourceEhcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private SysRoleResourceEhcacheService sysRoleResourceEhcacheService;

    // http://localhost:8080/MyArtifact/test.s
    @RequestMapping("/test.s")
    public ModelAndView chk() {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("test");
        mView.addObject("roleid", sysRoleResourceEhcacheService.selectByPrimaryKey(8).getRoleId());
        return mView; // 返回页面
    }

    // http://localhost:8080/MyArtifact/test.htm
    @RequestMapping(path = "/{test}")
    public void chk1(@PathVariable("test") String test) {
        System.out.println(test);
    }
}
