package com.controller.hanlder;

import com.compoent.IProcessorService;
import com.controller.ProcessorControllerAnnotation;
import com.po.SysRoleResource;
import com.service.SysRoleResourceService;
import com.utils.JSONUtil;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessorControllerAnnotation(value = "select")
public class SelectRoleProcess implements IProcessorService {

    @Autowired
    SysRoleResourceService sysRoleResourceService;

    @Override
    public <K, V, T> T execute(Map<K, V> param) {
        Integer id = Integer.valueOf((String) param.get("id"));
        SysRoleResource selectByPrimaryKey = sysRoleResourceService.selectByPrimaryKey(id);
        return (T) JSONUtil.toJSonString(selectByPrimaryKey);
    }

}
