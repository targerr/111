package test.java.com.wanggs.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;


/**
 * Created by Wgs on 2017/8/6.
 */

public class CoreServiceTest {
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    String appsecret;
    @Test
    public void processRequest() throws Exception {
        System.out.println(appid);
        System.out.println(appsecret);

    }



}
