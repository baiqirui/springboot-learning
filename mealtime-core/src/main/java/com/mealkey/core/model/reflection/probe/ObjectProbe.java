package com.mealkey.core.model.reflection.probe;

/**
 * 定义访问对象的成员的功能。
 * @author yidi
 */
public interface ObjectProbe
{
    /**
     * 通过类实现时，获取具有指定名称的值。
     * @param name 名称。
     * @return 具有指定名称的值。
     */
    Object get(String name);
    
    /**
     * 通过类实现时，设置指定名称的值。
     * @param name 名称。
     * @param value 值。
     */
    void set(String name, Object value);
}
