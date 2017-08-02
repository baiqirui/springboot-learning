package com.mealkey.core.exception;


import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 当将空引用传递给不接受它作为有效参数的方法时引发的异常。
 * @author yidi
 */
public class ArgumentNullException extends ArgumentException
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化 ArgumentNullException 类的新实例，并指定参数的名称。
     * @param argumentName 参数的名称。
     */
    public ArgumentNullException(String argumentName) 
    {
        this(ResourceUtil.getString(ArgumentNullException.class,
            "ArgumentNull"),argumentName);
    }

    /**
     * 初始化 ArgumentNullException 类的新实例，并指定错误信息
     * 和参数的名称。
     * @param message 解释异常原因的错误信息。
     * @param argumentName 导致异常的参数的名称。
     */
    public ArgumentNullException(String message, String argumentName)
    {
        super(message,argumentName);
    }
    
    /** 
     * 检测参数值是否为空引用。
     * @param argumentName 参数的名称。
     * @param actualValue 参数的实际值。
     */
    public static void checkNull(String argumentName, Object actualValue)
    {
        if (null == actualValue)
        {
            throw new ArgumentNullException(argumentName);
        }
    }
    
    /** 
     * 检测两个参数值是否为空引用。
     * @param argumentName1 参数的名称1。
     * @param actualValue1 参数的实际值1。
     * @param argumentName2 参数的名称2。
     * @param actualValue2 参数的实际值2。
     */
    public static void checkNull(String argumentName1, Object actualValue1, 
        String argumentName2, Object actualValue2)
    {
        if (null == actualValue1)
        {
            throw new ArgumentNullException(argumentName1);
        }
        if (null == actualValue2)
        {
            throw new ArgumentNullException(argumentName2);
        }
    }
}
