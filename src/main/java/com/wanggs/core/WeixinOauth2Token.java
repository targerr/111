package com.wanggs.core;

/**
 * Created by wanggs on 2017/7/1.
 * 网页授权信息
 */
public class WeixinOauth2Token {

    /**
     * access_token : ACCESS_TOKEN
     * expires_in : 7200
     * refresh_token : REFRESH_TOKEN
     * openid : OPENID
     * scope : SCOPE
     */

    //网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    private String accessToken;
    // access_token接口调用凭证超时时间，单位（秒）
    private int expiresIn;
    // 用户刷新access_token
    private String refreshToken;
    // 用户唯一标识
    private String openId;
    // 用户授权的作用域，使用逗号（,）分隔
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
