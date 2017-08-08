package com.wanggs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Wgs on 2017/8/6.
 */
@Controller
@RequestMapping("/")
public class HelloController {
    @Value("${wx.token}")
    private String token;
    @RequestMapping("/index")
    public String index(){
        System.out.println(token);
        return "hello";

    }

}
