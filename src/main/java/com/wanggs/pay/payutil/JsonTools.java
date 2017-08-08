package com.wanggs.pay.payutil;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonTools {
	public static String toJson(Object o) {
		return JSONObject.toJSONString(o, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
	}
}
