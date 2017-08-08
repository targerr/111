package com.wanggs.service;

import com.wanggs.core.message.BaseRespMessage;
import com.wanggs.core.message.ReqLocationMessage;
import com.wanggs.core.message.RespTextMessage;
import com.wanggs.core.util.MessageUtil;
import org.springframework.stereotype.Service;

/**
 * 处理地理位置
 */
@Service
public class LocationMessageService implements MessageService<ReqLocationMessage> {

	@Override
	public BaseRespMessage service(ReqLocationMessage reqMessage) {
		RespTextMessage textMessage = new RespTextMessage();

		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		textMessage.setContent("这是地理位置消息/::),\n您所在的位置为X:" + reqMessage.getLocation_X() + ",Y:" + reqMessage.getLocation_Y());

		return textMessage;
	}

}
