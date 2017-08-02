package com.mealkey.core.model.reflection.probe;

import java.util.Map;

/**
 * 提供访问 {@link Map} 对象的成员的功能。
 * @param V 值的类型。
 * @author yidi
 */
public class MapProbe<V> implements ObjectProbe
{
    private Map<String,V> map;
    
    /**
     * 初始化 MapProbe 类的新实例。
     * @param map Map 对象。
     */
    public MapProbe(Map<String,V> map)
    {
        this.map = map;
    }
    
    /**
     * 获取具有指定名称的值。
     * @param name 名称。
     * @return 具有指定名称的值。
     */
    public Object get(String name)
    {
        return map.get(name);
    }
    
    /**
     * 设置指定名称的值。
     * @param name 名称。
     * @param value 值。
     */
    @SuppressWarnings("unchecked")
    public void set(String name, Object value)
    {
        map.put(name,(V)value);
    }
}
