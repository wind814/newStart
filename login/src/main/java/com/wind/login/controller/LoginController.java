package com.wind.login.controller;

import com.wind.login.dao.AccountMapper;
import com.wind.login.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/record/{account}/")
public class LoginController {

        @Autowired
        private AccountMapper accountMapper;

        @Autowired
        private StringRedisTemplate redisTemplate;

        @RequestMapping(value = "login" ,method = RequestMethod.GET)
        public String login(@PathVariable("account") String account){

            Account vo = accountMapper.selectByPrimaryKey(1L);

            if(!vo.getAccountName().equals(account))
                return "ERROR for the account :"+account;

            return "token";

        }

        @RequestMapping(value = "getAccount" ,method = RequestMethod.GET)
        public String getAccount(@PathVariable("account") String account){
            return redisTemplate.opsForValue().get("name");
        }

}
