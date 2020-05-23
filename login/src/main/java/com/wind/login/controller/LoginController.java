package com.wind.login.controller;

import com.alibaba.druid.filter.config.ConfigTools;
import com.wind.login.dao.AccountMapper;
import com.wind.login.dao.RankScoreMapper;
import com.wind.login.entity.Account;
import com.wind.login.entity.RankScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/record/{account}/")
public class LoginController {

        @Autowired
        private AccountMapper accountMapper;

        @Autowired
        private StringRedisTemplate redisTemplate;

        @Resource
        private RankScoreMapper rankScoreMapper;

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

        @RequestMapping(value = "rankList" , method = RequestMethod.GET)
        public String getRankList(@PathVariable("account") Long id){

            RankScore rankScore = new RankScore(2L,"200");

            rankScoreMapper.insert(rankScore);

            rankScore = rankScoreMapper.selectByPrimaryKey(id);

            return rankScore.getScore();
        }


    public static void main(String[] args) {
        try {
            String password = "root";
            String[] arr = ConfigTools.genKeyPair(512);

            // System.out.println("privateKey:" + arr[0]);
            System.out.println("publicKey:" + arr[1]);
            System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
