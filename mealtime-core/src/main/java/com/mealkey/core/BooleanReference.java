package com.mealkey.core;

/**
 * 表示包含一个布尔值的引用类型。
 * @author yidi
 */
public class BooleanReference
{
    private boolean value;

    /**
     * 初始化 BooleanReference 类的新实例。
     */
    public BooleanReference()
    {
        value = false;
    }
    
    /**
     * 获取布尔值。
     * @return 布尔值。
     */
    public boolean getValue()
    {
        return value;
    }
    
    /**
     * 设置布尔值。
     * @param value 布尔值。
     */
    public void setValue(boolean value)
    {
        this.value = value;
    }
}
