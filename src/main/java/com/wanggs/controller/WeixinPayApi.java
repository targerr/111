package com.wanggs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class WeixinPayApi {
    @RequestMapping("/weixinPay")
    public void weixinPay(HttpServletRequest request, HttpServletResponse response){


    }

	
}
