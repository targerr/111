package com.wanggs.service;

import com.wanggs.core.message.*;
import com.wanggs.core.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 核心业务类
 */
@Service
public class MainMessageService {
	@Autowired
	private TextMessageService textMessageService;
	@Autowired
	private ImageMessageService imageMessageService;
	@Autowired
	private LinkMessageService linkMessageService;
	@Autowired
	private LocationMessageService locationMessageService;
	@Autowired
	private VoiceMessageService voiceMessageService;
	
	/**
	 * 处理消息
	 * @param reqMessage 消息对象
	 * @return 返回响应消息
	 */
	public BaseRespMessage service(BaseReqMessage reqMessage) {
		BaseRespMessage baseRespMessage = null;
		String msgType = reqMessage.getMsgType();
		
		if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) { // 处理文本消息
			baseRespMessage = textMessageService.service((ReqTextMessage)reqMessage);
		}else if(MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) { // 处理图片消息
			baseRespMessage = imageMessageService.service((ReqImageMessage)reqMessage);
		}else if(MessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType)) { // 处理链接消息
			baseRespMessage = linkMessageService.service((ReqLinkMessage)reqMessage);
		}else if(MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) { // 处理地理位置消息
			baseRespMessage = locationMessageService.service((ReqLocationMessage)reqMessage);
		}else if(MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) { // 处理声音消息
			baseRespMessage = voiceMessageService.service((ReqVoiceMessage)reqMessage);
		}else{
			baseRespMessage =  buildErrorRespMessage("请求处理异常，请稍候尝试！", reqMessage);
		}
		
		return baseRespMessage;
	}
	
	private BaseRespMessage buildErrorRespMessage(String error,BaseReqMessage reqMessage) {
		RespTextMessage respMessage = new RespTextMessage();
		respMessage.setContent(error);
		respMessage.setCreateTime(System.currentTimeMillis());
		respMessage.setFromUserName(reqMessage.getToUserName());
		respMessage.setToUserName(reqMessage.getFromUserName());
		return respMessage;
	}
}
