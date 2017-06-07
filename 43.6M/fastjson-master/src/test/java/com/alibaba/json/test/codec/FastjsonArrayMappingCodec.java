package com.alibaba.json.test.codec;

import java.io.OutputStream;
import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastjsonArrayMappingCodec implements Codec {

    private ParserConfig    config = ParserConfig.getGlobalInstance();

    public FastjsonArrayMappingCodec(){
        System.out.println("fastjson-" + JSON.VERSION);    
    }

    public String getName() {
        return "fastjson-BeanToArray";
    }

    public <T> T decodeObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz, Feature.DisableCircularReferenceDetect, Feature.SupportArrayToBean);
    }

    public <T> Collection<T> decodeArray(String text, Class<T> clazz) throws Exception {
        DefaultJSONParser parser = new DefaultJSONParser(text, config);
        parser.config(Feature.DisableCircularReferenceDetect, true);
        return parser.parseArray(clazz);
    }

    public final Object decodeObject(String text) {
        return JSON.parseObject(text, Feature.DisableCircularReferenceDetect, Feature.SupportArrayToBean);
    }

    public final Object decode(String text) {
        return JSON.parseObject(text, Feature.DisableCircularReferenceDetect, Feature.SupportArrayToBean);
    }

    // private JavaBeanSerializer serializer = new JavaBeanSerializer(Long_100_Entity.class);

    public String encode(Object object) throws Exception {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BeanToArray);
    }

    @SuppressWarnings("unchecked")
    public <T> T decodeObject(byte[] input, Class<T> clazz) throws Exception {
        return (T) JSON.parseObject(input, clazz, Feature.DisableCircularReferenceDetect, Feature.SupportArrayToBean);
    }

    @Override
    public byte[] encodeToBytes(Object object) throws Exception {
        return JSON.toJSONBytes(object, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BeanToArray);
    }

    @Override
    public void encode(OutputStream out, Object object) throws Exception {
       JSON.writeJSONString(out, object, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BeanToArray);
    }

}
