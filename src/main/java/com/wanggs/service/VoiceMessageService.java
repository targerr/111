package com.wanggs.service;

import com.wanggs.core.message.BaseRespMessage;
import com.wanggs.core.message.ReqVoiceMessage;
import com.wanggs.core.message.RespTextMessage;
import com.wanggs.core.util.MessageUtil;
import org.springframework.stereotype.Service;

/**
 * 处理语音
 */
@Service
public class VoiceMessageService implements MessageService<ReqVoiceMessage> {

	@Override
	public BaseRespMessage service(ReqVoiceMessage reqMessage) {
		RespTextMessage textMessage = new RespTextMessage();

		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);

		textMessage.setContent("这是语音消息/::)\n媒体ID:" + reqMessage.getMediaId());

		return textMessage;
	}

}
