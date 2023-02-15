package com.nfsn.convertor;

import com.alibaba.fastjson.JSON;
import com.nfsn.common.Message;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

@Slf4j
public class MessageEncoder implements javax.websocket.Encoder.Text<Message> {
    @Override
    public String encode(Message object) throws EncodeException {
        log.debug("自定义json解析聊天实体类");
        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}