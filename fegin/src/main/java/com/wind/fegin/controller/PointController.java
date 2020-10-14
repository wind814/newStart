package com.wind.fegin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wind on 20/8/1.
 */
@Controller
@RequestMapping("/v1/point/{account}/")
public class PointController {

    private static final Logger wind0415Single = LoggerFactory.getLogger("wind_0415_single");


    /**
     * you can add point in any time in a day
     * @return
     */
    @RequestMapping("addPoint/{point}")
    @ResponseBody
    public String addPointDay(@PathVariable String point,@PathVariable String account){

        String dayTime = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String key = "point:"+dayTime+account;

        wind0415Single.debug(dayTime);
        wind0415Single.debug(key);

        return dayTime;
    }



}
