package com.wanggs.service;

import com.wanggs.core.message.BaseRespMessage;
import com.wanggs.core.message.ReqImageMessage;
import com.wanggs.core.message.RespTextMessage;
import com.wanggs.core.util.MessageUtil;
import org.springframework.stereotype.Service;

/**
 * 处理图片
 */
@Service
public class ImageMessageService implements MessageService<ReqImageMessage> {

	@Override
	public BaseRespMessage service(ReqImageMessage reqMessage) {
		RespTextMessage textMessage = new RespTextMessage();

		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		textMessage.setContent("这是图片消息/::)\n图片地址:" + reqMessage.getPicUrl());

		return textMessage;
	}

}
