package com.example.myframe.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 类功能描述: 把对象转换成json格式数据
 *
 * @author wm
 * @date 16/3/9
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 方法功能描述: 把object转换成json格式的字符串
     *
     * @param obj 对象
     * @return json字符串
     *
     */
    public static String toJson(Object obj) {
        Writer writer = new StringWriter();
        String result = "";
        try {
            objectMapper.writeValue(writer, obj);
            result = writer.toString();
           // System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 方法功能描述: 将string反序列化成对象
     *
     * @param json json字符串
     * @param valueType 对象类型
     * @return 序列化后的对象
     *
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法功能描述: 将json array反序列化成对象
     *
     * @param json json字符串
     * @param typeReference 对象类型
     * @return 序列化后的对象
     *
     */
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
