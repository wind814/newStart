package com.wind.fegin.controller;

import com.wind.fegin.dao.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wind on 20/6/30.
 */
@RestController
@RequestMapping("v1/test/fegin/")
public class FeginController {

    @Autowired
    AccountClient accountClient;

    @RequestMapping("{test}")
    public String test(@PathVariable("test") String test){
        return accountClient.login(test);
    }

    @RequestMapping("class")
    public String getThisClass(){
        return this.getClass().toString();
    }

    public static void main(String[] args) {

        int i = 1;

        while(i-->0){
            System.out.print(i);
        }

    }
}
