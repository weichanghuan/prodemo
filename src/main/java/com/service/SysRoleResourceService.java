package com.service;

import com.po.SysRoleResource;

import java.util.List;

public interface SysRoleResourceService {

    public SysRoleResource selectByPrimaryKey(Integer id);

    public int insert(SysRoleResource record);

    public List<SysRoleResource> selectByAny(SysRoleResource sysRoleResource);
}
