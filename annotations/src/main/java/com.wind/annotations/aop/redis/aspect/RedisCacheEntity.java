package com.wind.annotations.aop.redis.aspect;


import com.alibaba.fastjson.JSON;
import com.wind.annotations.aop.redis.AspectAbs;
import com.wind.annotations.aop.redis.RedisUtil;
import com.wind.annotations.aop.redis.annotation.RedisCacheAspect;
import com.wind.annotations.aop.redis.constant.RedisCacheTypeEnum;

import com.wind.annotations.aop.redis.constant.RedisKeyEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
    @description:  redis缓存  注解切面
    @author wanghn-a
    @date 2018/1/5 17:24
**/
@Component
@Aspect
public class RedisCacheEntity extends AspectAbs {

//	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Pointcut("@annotation(com.wind.annotations.aop.redis.annotation.RedisCacheAspect)")
	public void RedisCachePointcut() {
	}

	@Around("RedisCachePointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args=pjp.getArgs();
		String id;
		if(args ==null ||args.length==0){
			id = null;
		}else {
			id = doArgsToString(args[0]);
		}
		RedisCacheAspect tla = super.getAnnotation(pjp, RedisCacheAspect.class);
		RedisKeyEnum redisKeyEnum = tla.redisKeyEnum();
		Class cl = tla.cl();
		RedisCacheTypeEnum resultType = tla.resultType();

		Object result;

		try{

			result = doGetRedisDataByType(resultType,redisKeyEnum,cl,id);
			if(result == null){
				Object o = pjp.proceed();
				String s = JSON.toJSONString(o);
				RedisUtil.getInstance().setString(redisKeyEnum,s,id);
				return o;
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return pjp.proceed();
		}

	}

	//参数转化
	public String doArgsToString(Object obj){

		if(Integer.class == obj.getClass()) {
			Integer idi = (Integer) obj;
			return idi.toString();
		} else if(Long.class == obj.getClass()) {
			Long idl = (Long) obj;
			return idl.toString();
		} else {
			return(String) obj;
		}
	}

	//区分结果类型  list  obj string
	public Object doGetRedisDataByType(RedisCacheTypeEnum resultType, RedisKeyEnum redisKeyEnum, Class cl, String id){

		if(RedisCacheTypeEnum.LIST.equals(resultType)) {
			return RedisUtil.getInstance().getListObject(redisKeyEnum,cl,id);
		}

		if(RedisCacheTypeEnum.OBJ.equals(resultType)) {
			return RedisUtil.getInstance().getObject(redisKeyEnum,cl,id);
		}

		if(RedisCacheTypeEnum.STRING.equals(resultType)) {

			return RedisUtil.getInstance().getString(redisKeyEnum,id);
		}

		return null;
	}

}
