package com.wind.login.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/record/{account}/")
public class LoginController {

        @RequestMapping(value = "login" ,method = RequestMethod.GET)
        public String login(@PathVariable("account") String acccount){

            if(!"wind".equals(acccount))
                return "ERROR for the account :"+acccount;

            return "token";

        }

}
