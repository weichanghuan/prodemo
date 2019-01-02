package com.compoent;

import com.dao.SysRoleResourceMapper;
import com.po.SysRoleResource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceEhcacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TEST_INFO = "testInfo";

    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;

    /**
     * @Cacheable 将结果加入缓存。下次调用时直接从缓存中读取 cacheNames指定缓存，要与ehcache.xml中的cache明显相同
     * @CacheEvict 删除缓存 beforeInvocation=true 表示在方法执行之前删除缓存 默认的 key=0 与下面
     * countUser()方法中key 相同
     * @CachePut更新缓存 将结果根据key 更新缓存中的内容
     */
    @Cacheable(cacheNames = TEST_INFO, key = "#id")
    public SysRoleResource selectByPrimaryKey(Integer id) {
        System.out.println("111");
        return sysRoleResourceMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = TEST_INFO, key = "#sysRoleResource.id")
    public List<SysRoleResource> selectByAny(SysRoleResource sysRoleResource) {
        logger.debug("进入Db查询数据");
        return sysRoleResourceMapper.selectByAny(sysRoleResource);
    }

}
