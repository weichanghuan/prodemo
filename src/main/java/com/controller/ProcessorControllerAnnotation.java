package com.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @description:(TODO 请在此添加描述)
 * @reason:TODO ADD REASON(可选)
 * @author weichanghuan
 * @date 2017年5月24日 下午6:13:07
 * @since JDK 1.6
 */
@Documented
// @Documented 将此注解包含在 javadoc 中 ，它代表着此注解会被javadoc工具提取成文档。
// 在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。
@Target(ElementType.TYPE)
// @Target 表示该注解用于什么地方，可能的值在枚举类 ElemenetType 中，包括：
// ElemenetType.CONSTRUCTOR----------------------------构造器声明
// ElemenetType.FIELD --------------------------------------域声明（包括 enum 实例）
// ElemenetType.LOCAL_VARIABLE------------------------- 局部变量声明
// ElemenetType.METHOD ----------------------------------方法声明
// ElemenetType.PACKAGE --------------------------------- 包声明
// ElemenetType.PARAMETER ------------------------------参数声明
// ElemenetType.TYPE--------------------------------------- 类，接口（包括注解类型）或enum声明
@Retention(RetentionPolicy.RUNTIME)
// @Retention 表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括：
// RetentionPolicy.SOURCE ---------------------------------注解将被编译器丢弃
// RetentionPolicy.CLASS-----------------------------------注解在class文件中可用，但会被VM丢弃
// RetentionPolicy.RUNTIME VM-------将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
public @interface ProcessorControllerAnnotation {
    String value() default "base";

}
