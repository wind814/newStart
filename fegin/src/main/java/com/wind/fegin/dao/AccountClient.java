package com.wind.fegin.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wind on 20/6/30.
 */
@FeignClient("loginService")
public interface AccountClient {

    @RequestMapping(value = "/v1/record/{account}/login",method = RequestMethod.GET)
    String login(@PathVariable("account") String account);
}
