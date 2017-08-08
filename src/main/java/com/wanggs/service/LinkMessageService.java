package com.wanggs.service;

import com.wanggs.core.message.BaseRespMessage;
import com.wanggs.core.message.ReqLinkMessage;
import com.wanggs.core.message.RespTextMessage;
import com.wanggs.core.util.MessageUtil;
import org.springframework.stereotype.Service;

/**
 * 处理连接
 */
@Service
public class LinkMessageService implements MessageService<ReqLinkMessage> {

	@Override
	public BaseRespMessage service(ReqLinkMessage reqMessage) {
		RespTextMessage textMessage = new RespTextMessage();

		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		textMessage.setContent("这是链接消息/::)\n您发的连接为:" + reqMessage.getUrl());

		return textMessage;
	}

}
