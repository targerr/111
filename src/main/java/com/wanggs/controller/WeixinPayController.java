package com.wanggs.controller;


import com.foxinmy.weixin4j.http.weixin.XmlResult;
import com.foxinmy.weixin4j.payment.mch.Order;
import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.IOUtil;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.xml.ListsuffixResultDeserializer;
import com.foxinmy.weixin4j.xml.XmlStream;
import com.wanggs.pay.payutil.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Wgs on 2017/8/6.
 */
@Controller()
@RequestMapping("/")
public class WeixinPayController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinPayController.class);


    @RequestMapping("/pay")

    public String pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String userId = request.getParameter("userId");
        String userId = "001";

        //String orderNo = request.getParameter("orderNo");
        String orderNo = "";

        String money = "1";  //request.getParameter("money");
        // String openId = request.getParameter("openid");
        float sessionmoney = Float.parseFloat(money);
        String finalmoney = String.format("%.2f", sessionmoney);
        finalmoney = finalmoney.replace(".", "");
        String appid = "wxd898fcb01713c658";
        //String appsecret = "null";
        //String partner = "null";
        String partnerkey = "C5245D70627C1F8E9964D494B0735025";
        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = TenpayUtil.buildRandom(4) + "";
        String strReq = strTime + strRandom;
        String mch_id = "1483469312";
        //String device_info = "";
        String nonce_str = strReq;
        String body = "48路家居商品";
        String attach = userId;
        //String out_trade_no = orderNo;
        String out_trade_no = System.currentTimeMillis() + "";
        int intMoney = Integer.parseInt(finalmoney);
        int total_fee = intMoney;
        String spbill_create_ip = request.getRemoteAddr();
        // ResourceBundle resourceBundle = ResourceBundle.getBundle("ezforym");
        String notify_url = "http://wanggss.nat300.top/index";//resourceBundle.getString("ezforym.notify");
        // 参数类型 二维码连接
        String trade_type = "NATIVE";
        //String trade_type = "JSAPI";
        String openid = "oTgZpwZZJhnOcgXXNYUdICP4RuMM";
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", finalmoney);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("openid", openid);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(appid, partnerkey);
        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
                + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>"
                + "<attach>" + attach + "</attach>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
                + "<total_fee>" + total_fee + "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip
                + "</spbill_create_ip>" + "<notify_url>" + notify_url + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>" + "<openid>" + openid + "</openid>" + "</xml>";
        System.out.println(xml);
        String allParameters = "";
        try {
            allParameters = reqHandler.genPackage(packageParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String prepay_id = "";
        try {
            prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
            if (prepay_id.equals("")) {
                request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String appid2 = appid;
        String timestamp = Sha1Util.getTimeStamp();
        String nonceStr2 = nonce_str;
        String prepay_id2 = "prepay_id=" + prepay_id;
        String packages = prepay_id2;
        finalpackage.put("appId", appid2);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonceStr2);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalsign = reqHandler.createSign(finalpackage);
        PrintWriter out = response.getWriter();
        Packages p = new Packages();
        p.setAppId(appid2);
        p.setTimeStamp(timestamp);
        p.setNonceStr(nonceStr2);
        p.setPackages(packages);
        p.setSignType("MD5");
        p.setFinalsign(finalsign);
        System.out.println(p.toString());

        request.setAttribute("p", p);

        // request.getRequestDispatcher("pay.jsp").forward(request,response);
        return "pay";
    }

    @RequestMapping("/weixinNotify")
    @ResponseBody
    public String payNotify(HttpServletRequest request) throws IOException {

        //获取订单信息
        String content = StringUtil.newStringUtf8(IOUtil.toByteArray(request.getInputStream()));
        Order order = ListsuffixResultDeserializer.deserialize(content,
                Order.class);
        logger.info("jsapi_notify_order_info:{}", order);
        //验证签名
        String sign = order.getSign();
    /*    String valid_sign = weixinPayProxy.getWeixinSignature().sign(order);
        logger.info("微信签名----->sign={},vaild_sign={}", sign, valid_sign);
        if (!sign.equals(valid_sign)) {
            return XmlStream.toXML(new XmlResult(Consts.FAIL, "签名错误"));
        }*/
        // TODO 处理业务逻辑，如没有特殊要求可以考虑单独启一个线程去处理自己的业务，对于微信签名过后就可以返回success了。
        logger.info("支付成功:{}", order);

        String outTradeNo = order.getOutTradeNo();
        //  Long orderId = orderIdMap.get(outTradeNo);
//        orderIdMap.remove(outTradeNo);

        // 需要ajax的形式返回给微信，保证返回值能写到ResponseInputStream就行，Spring mvc使用 @ResponseBody注解，Servlet使用HttpServletResponse#getWrite#write
        return XmlStream.toXML(new XmlResult(Consts.SUCCESS, ""));
    }
}
