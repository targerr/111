package com.wanggs.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wanggs.core.message.BaseReqMessage;
import com.wanggs.core.message.ReqMessageFactory;
import com.wanggs.core.util.LOG;
import com.wanggs.core.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;

import java.util.Map;

/**
 * Created by Wgs on 2017/8/6.
 */
@Service
public class CoreService {

    @Autowired
    private MainMessageService mainMessageService;
    public  String processRequest(HttpServletRequest req) throws IOException {

        //调用parseXml方法解析请求消息
        Map<String, String> requestMap = MessageUtil.parseXml(req);

        LOG.info("接受xml{}", requestMap);

        BaseReqMessage reqMessage = ReqMessageFactory.build(requestMap);


        xStream.alias("xml", mainMessageService.service(reqMessage).getClass());

        String result = xStream.toXML(mainMessageService.service(reqMessage));
        LOG.info("响应数据{}", result);
        return result;

    }


    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xStream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
