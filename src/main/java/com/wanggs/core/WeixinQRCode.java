package com.wanggs.core;

/**
 * 临时二维码
 * Created by wanggs on 2017/7/1.
 */
public class WeixinQRCode {
    // 获取的二维码ticket
    private String ticket;
    // 二维码的有效时间，单位为秒，最大不超过1800
    private int expireSeconds;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
