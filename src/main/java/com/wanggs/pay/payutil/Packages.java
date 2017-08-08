/** 
 * Project Name:48lu-project
 * File Name:Package.java 
 * Package Name:com.utils 
 * Date:2016年9月2日上午10:37:57 
 * Copyright (c) 2016, 48lu科技有限公司
 * 
 */
package com.wanggs.pay.payutil;

/**
 * Package
 * 
 * @author 吴易泽
 *
 * @version v1.0.0
 *
 * @date 2016年9月2日
 *
 */
public class Packages {
	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String packages;
	private String signType;
	private String finalsign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getFinalsign() {
		return finalsign;
	}

	public void setFinalsign(String finalsign) {
		this.finalsign = finalsign;
	}
}
