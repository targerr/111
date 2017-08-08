package com.wanggs.service;

import com.wanggs.core.message.BaseRespMessage;
import com.wanggs.core.message.ReqTextMessage;
import com.wanggs.core.message.RespTextMessage;
import com.wanggs.core.util.MessageUtil;
import org.springframework.stereotype.Service;


/**
 * 文本消息处理
 */
@Service
public class TextMessageService implements MessageService<ReqTextMessage> {

	@Override
	public BaseRespMessage service(ReqTextMessage reqMessage) {
		RespTextMessage textMessage = new RespTextMessage();

		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		textMessage.setContent("这是文本消息/::)\n您发的文本消息为:" + reqMessage.getContent());

		return textMessage;
	}

}
