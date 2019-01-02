package com.service.impl;

import com.dao.SysRoleResourceMapper;
import com.po.SysRoleResource;
import com.service.SysRoleResourceService;

import java.util.List;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Autowired
    SysRoleResourceMapper sysRoleResourceMapper;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "test-queue")
    private Destination destination;

    public SysRoleResource selectByPrimaryKey(Integer id) {
        return sysRoleResourceMapper.selectByPrimaryKey(id);

    }

    @Override
    public int insert(SysRoleResource record) {
        // 生成id
        final long id = record.getId();
        // 发送一个消息到mq消息队列
        jmsTemplate.send(destination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                // 发送商品id
                TextMessage textMessage = session.createTextMessage(id + "");
                return textMessage;
            }
        });
        return sysRoleResourceMapper.insert(record);
    }

    public List<SysRoleResource> selectByAny(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.selectByAny(sysRoleResource);
    }

}
