package com.compoent;

import com.utils.JSONUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @className HandlerServiceAspect
 * @description 接口类处理切面
 * @author 危常焕
 * @date 2015-3-23 下午5:24:31
 * 
 */
@Service("handlerServiceAspect")
public class HandlerServiceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    public Object doAround(ProceedingJoinPoint pjp) {
        logger.info("init---------------------");
        long start = System.currentTimeMillis();
        // 获取方法参数
        Object[] args = pjp.getArgs();
        logger.debug("入参为：" + JSONUtil.toJSonString(args));
        /**
         * 任何通知（Advice）方法可以将第一个参数定义为
         * org.aspectj.lang.JoinPoint类型。JoinPoint接口提供了一系列有用的方法， 比如
         * getArgs()（返回方法参数） getThis() （返回代理对象） getTarget() （返回目标）
         * getSignature()（返回正在被通知的方法相关信息） toString() （打印出正在被通知的方法的有用信息。
         * 其中getSignature()返回的Signature对象可强制转换为MethodSignature，其功能非常强大，能获取包括参数名称在内的一切方法信息。
         */
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        String uuid = UUID.randomUUID().toString();
        HashMap<String, Object> requestMap = null;
        if (args != null && args.length > 0) {
            // requestMap = (HashMap<String, Object>) args[0];

        }

        try {
            Object proceed = pjp.proceed();
            // 方法执行后的业务处理
            long time = System.currentTimeMillis() - start;
            logger.debug("调用方法结束，所有时间" + time);
            logger.debug("响应参数为：" + JSONUtil.toJSonString(proceed));
            return proceed;
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e;
        }

    }

}