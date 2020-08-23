package com.lan.jackson.propertyfilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Map;


public class JacksonChangeValue {

    public static void main(String[] args) throws JsonProcessingException {
//        Object returenObj = "{\"msg\":\"success\",\"code\":\"0000\",\"data\":[{\"data\":[{\"address\":null,\"id\":\"000\"}],\"groupId\":1,\"sttr\":\"1\",\"title\":\"“回天有我”出实招，民主议事解难题！\",\"type\":3.0},{\"data\":[{\"address\":\"000\",\"id\":\"000\"}],\"groupId\":2,\"sttr\":\"2\",\"title\":\"“回天有我”出实招，民主议事解难题！\",\"type\":3.0},{\"data\":[{\"address\":\"000\",\"id\":\"000\"}],\"groupId\":3,\"sttr\":\"3\",\"title\":\"“回天有我”出实招，民主议事解难题！\",\"type\":3.0}]}";
//        returenObj= JSON.parseObject((String) returenObj);
        // 数组
        Object returenObj = "[{\"name\":null,\"url\":\"www.runoob.com\"},{\"name\":\"google\",\"url\":\"www.google.com\"},{\"name\":\"微博\",\"url\":\"www.weibo.com\"}]";
        returenObj= JSON.parseArray((String) returenObj);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);

        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.addMixIn(Object.class, PropertyFilterMixIn.class);

        String finalApiBodyDims = "msg";
        FilterProvider filters = new SimpleFilterProvider().addFilter(
                "propertyFilterMixIn", new SimpleBeanPropertyFilter(){
                    @Override
                    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                        JsonStreamContext ctx = jgen.getOutputContext();
                        String fullName = getFullName(ctx.getParent(), true);
                        fullName = fullName.replaceAll("\\{\"", "").replaceAll("\"}", "").replaceAll("/->", "");
                        String fieldName = writer.getName();
                        String fieldFullPath = "";
                        if (fullName.equals("/")) {
                            fieldFullPath = fieldName;
                        } else {
                            fieldFullPath = fullName + "->" + fieldName;
                        }

                        // 转换条件
                        if (fieldName.equals(finalApiBodyDims)) {
                            writer.serializeAsField("转换成功", jgen, provider);
                        } else {
                            writer.serializeAsField(pojo, jgen, provider);
                        }
                    }

                    String fullName = "";

                    private String getFullName(JsonStreamContext ctx, boolean first) {
                        if (first) fullName = "";
                        if (ctx.getParent() != null) {
                            if (ctx.inObject()) {
                                fullName = getFullName(ctx.getParent(), false) + "->" + ctx.toString();
                            } else {
                                fullName = getFullName(ctx.getParent(), false);
                            }
                        } else {
                            fullName += ctx.toString();
                        }
                        return fullName;
                    }

                    @Override
                    public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider provider,
                                                   PropertyWriter writer)
                            throws Exception
                    {

                    }
                });
        mapper.setFilterProvider(filters);
        try {
            String result = mapper.writeValueAsString(returenObj);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
