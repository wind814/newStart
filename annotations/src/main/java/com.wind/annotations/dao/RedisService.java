package com.wind.annotations.dao;

import com.wind.annotations.aop.redis.RedisUtil;
import com.wind.annotations.aop.redis.annotation.RedisCacheAspect;
import com.wind.annotations.aop.redis.constant.RedisCacheTypeEnum;
import com.wind.annotations.aop.redis.constant.RedisKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wind on 20/8/1.
 */
@Component
public class RedisService {


    @Autowired
    private RedisUtil redisUtil;


//    @RedisCacheAspect(redisKeyEnum = RedisKeyEnum.WIND_POINT,cl= String.class,resultType = RedisCacheTypeEnum.STRING)
    public String addString(String date,String account,String value){
        redisUtil.setString(RedisKeyEnum.WIND_POINT,value,date,account);
        return redisUtil.getString(RedisKeyEnum.WIND_POINT,date,account);
    }

    public String getString(String date,String account){
        return redisUtil.getString(RedisKeyEnum.WIND_POINT,date,account);
    }





}
