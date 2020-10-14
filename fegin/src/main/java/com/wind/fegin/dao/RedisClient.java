package com.wind.fegin.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wind on 20/8/1.
 */
@FeignClient("redisService")
public interface RedisClient {

    @RequestMapping(value = "/v1/redis/{account}/point/addString/{date}/{value}",method = RequestMethod.GET)
    String addString(@PathVariable String account,@PathVariable String date, @PathVariable String value);
}
