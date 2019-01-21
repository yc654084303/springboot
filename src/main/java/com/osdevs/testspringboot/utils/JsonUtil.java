package com.osdevs.testspringboot.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static Object parse(String json) {
        return JSON.parse(json);
    }

}
