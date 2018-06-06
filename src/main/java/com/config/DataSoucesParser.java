package com.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;


public class DataSoucesParser implements BeanDefinitionParser {

    public static final String DATE_SOURCES = "dataSources";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        doParse(element, parserContext);
        return null;
    }

    private synchronized void doParse(Element element, ParserContext parserContext) {
        //获取xml的中自定义标签的值
        String id = element.getAttribute("id");
        String url = element.getAttribute("url");
        String userName = element.getAttribute("userName");
        String passWord = element.getAttribute("passWord");

        RootBeanDefinition def = new RootBeanDefinition(DataSouces.class);
        def.getPropertyValues().addPropertyValue("id",id);
        def.getPropertyValues().addPropertyValue("url",url);
        def.getPropertyValues().addPropertyValue("userName",userName);
        def.getPropertyValues().addPropertyValue("passWord",passWord);
        parserContext.getRegistry().registerBeanDefinition(DATE_SOURCES, def);

    }
}
