
package com.wanggs.controller;

import com.wanggs.core.ValidatePojo;
import com.wanggs.core.util.SignUtil;
import com.wanggs.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wgs on 2017/8/6.
 */

@Controller
@RequestMapping("/")
public class WeiXinController {
    // 提供给微信公众平台的token
    @Value("${wx.token}")
    private String token;

    private Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    @Autowired
    private CoreService coreService;

    /**
     * 处理微信服务器发来的验证请求
     *
     * @param validatePojo
     * @return 验证成功返回随机字符串
     */
    @RequestMapping(value = "service.do", method = RequestMethod.GET
            // 这句一定要加,如果不加默认会返回Content-Type=text/html导致token认证失败
            , produces = "text/plain")
    @ResponseBody
    public String validate(ValidatePojo validatePojo) {
        logger.info("{}-{}-{}-{}", validatePojo.getTimestamp(), validatePojo.getEchostr(), validatePojo.getNonce(), validatePojo.getSignature());
        if (SignUtil.checkSignature(validatePojo, token)) {
            return validatePojo.getEchostr();
        }
        return "";
    }

    /**
     * 处理微信服务器发来的请求
     *
     * @return 返回响应消息
     */
    @RequestMapping(value = "service.do", method = RequestMethod.POST,

            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String result = coreService.processRequest(request);

        return result;
    }


}

