package com.wind.annotations.controller;

import com.wind.annotations.aop.redis.RedisUtil;
import com.wind.annotations.aop.redis.constant.RedisKeyEnum;
import com.wind.annotations.dao.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wind on 20/8/1.
 */
@RestController
@RequestMapping("/v1/redis/{account}/point")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;




    /**
     * 根据account+date为key,新增数据value
     * @param account
     * @param date
     * @param value
     * @return
     */
    @RequestMapping(value = "addString/{date}/{value}",method = RequestMethod.GET)
    public String addString(@PathVariable String account,@PathVariable String date, @PathVariable String value){
        redisService.addString(date,account,value);
        return redisService.getString(date,account);
    }


    /**
     * 根据account+date删除当天所有坐标数据
     * @param account
     * @param date
     * @return
     */
    @RequestMapping(value = "deleteString/{date}",method = RequestMethod.GET)
    public Long deleteString(@PathVariable String account,@PathVariable String date){
        return redisUtil.delete(RedisKeyEnum.WIND_POINT,date,account);
    }


    /**
     * 新增用户每日坐标数据
     * @param account
     * @param value
     * @return
     */
    @RequestMapping(value = "addDateString",method = RequestMethod.POST)
    public String addDateString(@PathVariable String account,String value){

        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        redisUtil.setString(RedisKeyEnum.WIND_POINT,value,date,account);
        return redisUtil.getString(RedisKeyEnum.WIND_POINT,date,account);
    }


    /**
     * 按数量  循环增加
     * @param account
     * @param value
     * @param num
     * @return
     */
    @RequestMapping(value = "addNumString",method = RequestMethod.POST)
    public String addNumString(@PathVariable String account,String value,int num){

        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        redisUtil.setString(RedisKeyEnum.WIND_POINT,value,date,account);

        for(int i= 0 ; i<num;i++){

            String key = "wind" + new Random().nextInt(num);
            redisUtil.setString(RedisKeyEnum.WIND_POINT,value,date,key);
        }

        return redisUtil.getString(RedisKeyEnum.WIND_POINT,date,account);
    }

    public static void main(String[] args) {


        System.out.print(new Random().nextInt(100));

    }


}
