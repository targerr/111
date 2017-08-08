package com.wanggs.controller;

import com.wanggs.core.SNSUserInfo;
import com.wanggs.core.WeixinOauth2Token;
import com.wanggs.core.util.AdvancedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Wgs on 2017/8/6.
 */
@Controller
@RequestMapping("/")
public class OAuthServlet {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;


    private Logger logger = LoggerFactory.getLogger(OAuthServlet.class);

    @RequestMapping("/hello")
    public String oauth(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("gb2312");
        resp.setCharacterEncoding("gb2312");
        //用户同意后获取code
        String code = req.getParameter("code");
        logger.debug("code:{}==>>", code);

        //获取Access_token
        WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appid, appsecret, code);

        String accessToken = weixinOauth2Token.getAccessToken();

        logger.debug("网页授权接口访问凭证: >>>>>>>>>>>>>>>>>>>{}", accessToken);
        // 用户标识
        String openId = weixinOauth2Token.getOpenId();
        logger.debug("用户标识:>>>>>>>>>>>>>>>>>>>-{}", openId);
        // 获取用户信息
        SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
        logger.debug("{}", snsUserInfo);
        // 设置要传递的参数
        req.setAttribute("snsUserInfo", snsUserInfo);

        return "oauth";
    }
}
