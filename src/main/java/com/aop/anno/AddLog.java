package com.aop.anno;

import java.lang.annotation.*;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddLog {
}
