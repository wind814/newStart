package com.wind.annotations.aop.redis;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
    @description: 切面抽象类
    @author wanghn-a
    @date 2018/1/5 17:37
**/
public abstract class AspectAbs {
	protected Logger logger = LoggerFactory.getLogger(AspectAbs.class);
	

	protected Method getTargetMethod(JoinPoint jp){
		Method targetMethod = null;
		String m= jp.getSignature().toLongString();
		Method[] methods =jp.getTarget().getClass().getMethods();
		
		for(Method method :methods){
			if(m.equals(method.toString())){
				targetMethod=method;
				break;
			}
		}
		return targetMethod;
	}

	protected Object proceed(ProceedingJoinPoint jp, Object[] args) throws Throwable {
		if(args==null || args.length==0){
			return jp.proceed();
		}
		return jp.proceed(args);
	}
	
	protected  <T extends Annotation> T  getAnnotation(JoinPoint jp , Class<T> c){
		Method targetMethod = this.getTargetMethod(jp);
		return targetMethod.getAnnotation(c);
	}
}
