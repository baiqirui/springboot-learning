package com.mealkey.core.util;

import com.google.gson.Gson;


/**
 * 
 * json解析工具类（利用google的Gson进行解析）
 * 
 * @author baiqirui
 * @version [版本号, 2012-4-26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GsonUtil
{
    /**
     * Logger for this class
     */
    private static Gson gson  = new Gson();
    
    /**
     * 把json串转为指定的对象
     * 
     * @param <T>
     */
    public static <T> T fromJson(String str, Class<T> clazz)
    {
        return (T)gson.fromJson(str, clazz);
        
    }
    
    /**
     * 把对象转为json串
     */
    public static String toJson(Object obj)
    {
        return gson.toJson(obj);
    }
    
}
