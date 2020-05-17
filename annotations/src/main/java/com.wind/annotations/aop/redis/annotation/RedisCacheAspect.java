package com.wind.annotations.aop.redis.annotation;

import com.wind.annotations.aop.redis.constant.RedisCacheTypeEnum;
import com.wind.annotations.aop.redis.constant.RedisKeyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
    @description: redis缓存  注解切面
    @author wanghn-a
    @date 2018/1/5 17:36
**/

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface RedisCacheAspect {

    RedisKeyEnum redisKeyEnum();
    Class cl();
    RedisCacheTypeEnum resultType() default RedisCacheTypeEnum.LIST;
    String targetName() default "方法";
}
