package com.github.eugeneheen.berry.kit.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.eugeneheen.berry.kit.exception.JsonParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * Json工具箱，提供JSON字符串、对象、Map之间的互转操作
 * @author Eugene
 */
public class JsonKit {
    /**
     * ObjectMapper类型的JSON解析器
     */
    private ObjectMapper objectMapper;

    public JsonKit() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * 解析JSON字符串为一个指定类型的对象
     * @param json JSON字符串
     * @param clazz 转换输出类型
     * @param <T> 泛型定义
     * @return 指定类型的对象
     * @throws JsonParseException JSON解析异常
     */
    public <T> T read(String json, Class<T> clazz) throws JsonParseException {
        T t;
        try {
            t = this.objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonParseException("JSON字符串转换为对象:" + clazz.getName() + "，时发生异常!", e);
        }
        return t;
    }

    /**
     * 解析JSON文件流为一个指定类型的对象
     * @param file JSON文件流
     * @param clazz 转换输出类型
     * @param <T> 泛型定义
     * @return 指定类型的对象
     * @throws JsonParseException JSON解析异常
     */
    public <T> T read(InputStream file, Class<T> clazz) throws JsonParseException {
        T t;
        try {
            t = this.objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new JsonParseException("JSON文件流转换为对象:" + clazz.getName() + "，时发生异常!", e);
        }
        return t;
    }

    /**
     * 解析JSON字符串为一个Map对象
     * @param json JSON字符串
     * @return Map对象
     * @throws JsonParseException JSON解析异常
     */
    public Map<String, Object> readAsMap(String json) throws JsonParseException {
        Map<String, Object> map;
        try {
            map = this.objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new JsonParseException("JSON文件流转换为对象Map对象时发生异常!", e);
        }
        return map;
    }

    /**
     * 解析JSON文件流为一个Map对象
     * @param file JSON文件流
     * @return Map对象
     * @throws JsonParseException JSON解析异常
     */
    public Map<String, Object> readAsMap(InputStream file) throws JsonParseException {
        Map<String, Object> map;
        try {
            map = this.objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            throw new JsonParseException("JSON文件流转换为对象Map对象时发生异常!", e);
        }
        return map;
    }

    /**
     * 解析JSON字符串为一个自定义的集合
     * @param json JSON字符串
     * @param collectionClazz 转换结果的集合Class类型
     * @param clazz 转换结果集合存放的元素Class类型
     * @param <T> 泛型定义
     * @return Collection集合
     * @throws JsonParseException JSON解析异常
     */
    public <T> T readAsCollection(String json, Class<? extends Collection> collectionClazz, Class<?> clazz) throws JsonParseException {
        T t;
        try {
            t = this.objectMapper.readValue(json, this.getCollectionType(collectionClazz, clazz));
        } catch (IOException e) {
            throw new JsonParseException("JSON字符串转换为集合类型，时发生异常!", e);
        }

        return t;
    }

    /**
     * 解析JSON文件流串为一个自定义的集合
     * @param inputStream JSON文件流
     * @param collectionClazz 转换结果的集合Class类型
     * @param clazz 转换结果集合存放的元素Class类型
     * @param <T> 泛型定义
     * @return Collection集合
     * @throws JsonParseException JSON解析异常
     */
    public <T> T readAsCollection(InputStream inputStream, Class<? extends Collection> collectionClazz, Class<?> clazz) throws JsonParseException {
        T t;
        try {
            t = this.objectMapper.readValue(inputStream, this.getCollectionType(collectionClazz, clazz));
        } catch (IOException e) {
            throw new JsonParseException("JSON文件流转换为集合类型，时发生异常!", e);
        }

        return t;
    }

    /**
     * 解析Map集合为JSON字符串
     * @param map Map集合
     * @return JSON字符串
     * @throws JsonParseException JSON解析异常
     */
    public String write(Map<String, Object> map) throws JsonParseException {
        String result;
        try {
            result = this.objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(map + "转换为JSON字符串发生异常", e);
        }
        return result;
    }

    /**
     * 解析Map集合为JSON字符串
     * @param object Java对象，Tip:Collection实现类List、Set也可直接转换
     * @return JSON字符串
     * @throws JsonParseException JSON解析异常
     */
    public String write(Object object) throws JsonParseException {
        String result;
        try {
            result = this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(object.getClass().getCanonicalName() + "转换为JSON字符串发生异常", e);
        }
        return result;
    }

    /**
     * 获取一个实现Collection接口的集合结果类型
     * @param collectionClazz 集合类型Class
     * @param clazz 集合存放元素的Class类型
     * @return 结果集合类型
     */
    public JavaType getCollectionType(Class<? extends Collection> collectionClazz, Class<?> clazz) {
        return this.objectMapper.getTypeFactory().constructParametricType(collectionClazz, clazz);
    }
}
