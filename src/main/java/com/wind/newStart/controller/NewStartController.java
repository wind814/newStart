package com.wind.newStart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/v0415/wind/{id}/newStart")
public class NewStartController {


    @RequestMapping(value="/getStart",method = RequestMethod.GET)
    public String getStart(@PathVariable("id") String id) {
        return id;
    }

}
