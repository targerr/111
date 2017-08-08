package com.wanggs.service;


import com.wanggs.core.message.BaseReqMessage;
import com.wanggs.core.message.BaseRespMessage;

public interface MessageService<ReqMessage extends BaseReqMessage> {
	BaseRespMessage service(ReqMessage reqMessage);
}
