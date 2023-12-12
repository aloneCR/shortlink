package com.xpluo.shortlink.admin.common.serialize;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 手机号脱敏反序列化
 *
 * @author luoxiaopeng
 * @date 2023/12/12
 */
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String desensitizedPhone = DesensitizedUtil.mobilePhone(s);
        jsonGenerator.writeString(desensitizedPhone);
    }
}
