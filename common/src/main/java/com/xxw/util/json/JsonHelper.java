package com.xxw.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    private JsonHelper() {}

    /**
     * 对象装JSON字符串
     * @param obj 对象
     * @return  JSON 字符串
     */
    public static String objToString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * JSON字符串转对象
     * @param str JSON字符串
     * @param cls 对象类型
     * @return 对象
     */
    public static Object strToObject(String str, Class cls){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, cls);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
