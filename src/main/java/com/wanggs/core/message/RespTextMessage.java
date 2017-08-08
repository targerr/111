package com.wanggs.core.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("xml")
public class RespTextMessage extends BaseRespMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
