package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 当参数值超出调用的方法所定义的允许取值范围时引发的异常。
 * @author yidi
 */
public class ArgumentOutOfRangeException extends ArgumentException
{
    private static final long serialVersionUID = 1L;
    
    private Object actualValue;

    /**
     * 初始化 ArgumentOutOfRangeException 类的新实例，并指定导致异常的
     * 参数名和参数的实际值。
     * @param argumentName 参数的名称。
     * @param actualValue 参数的实际值。
     */
    public ArgumentOutOfRangeException(String argumentName, Object actualValue)
    {
        this(ResourceUtil.getString(ArgumentOutOfRangeException.class,
            "ArgumentOutOfRange"),argumentName,actualValue);
    }

    /**
     * 初始化 ArgumentOutOfRangeException 类的新实例，并指定错误信息、
     * 导致异常的参数名和参数的实际值。
     * @param message 解释异常原因的错误信息。
     * @param argumentName 参数的名称。
     * @param actualValue 参数的实际值。
     */
    public ArgumentOutOfRangeException(String message, 
        String argumentName, Object actualValue)
    {
        super(message,argumentName);
        this.actualValue = actualValue;
    }
    
    /**
     * 创建 ArgumentOutOfRangeException 异常，并指定导致异常的原因为
     *“值必须介于两值之间”。
     * @param argumentName 参数的名称。
     * @param actualValue 参数的实际值。
     * @param lowerValue 下限值。
     * @param upperValue 上限值。
     * @return ArgumentOutOfRangeException 异常。
     */
    public static ArgumentOutOfRangeException createBetween
        (String argumentName, Object actualValue, Object lowerValue, 
        Object upperValue)
    {
        return new ArgumentOutOfRangeException(ResourceUtil.getString
            (ArgumentOutOfRangeException.class,
            "ArgumentOutOfRange_Between",new Object[] {lowerValue,
            upperValue}),argumentName,actualValue);
    }
    
    /** 
     * 创建 ArgumentOutOfRangeException 异常，并指定导致异常的原因为
     * “索引超出范围”。
     * @param argumentName 参数的名称。
     * @param actualValue 参数的实际值。
     * @return ArgumentOutOfRangeException 异常。
     */
    public static ArgumentOutOfRangeException createIndex
        (String argumentName, Object actualValue)
    {
        return new ArgumentOutOfRangeException(ResourceUtil.getString
            (ArgumentOutOfRangeException.class,"ArgumentOutOfRange_Index"),
            argumentName,actualValue);
    }
    
    /**
     * 获取错误信息和参数名、无效参数值的字符串表示形式。
     * @return 错误信息和参数名、无效参数值的字符串表示形式。
     */
    @Override
    public String getMessage()
    {
        if (null != actualValue)
        {
            return super.getMessage() + Environment.NewLine 
                + ResourceUtil.getString(ArgumentOutOfRangeException.class,
                "ArgumentOutOfRange_ActualValue",new Object[] {actualValue});
        }
        return super.getMessage();
    }

    /** 
     * 获取导致异常的参数的实际值。
     * @return 导致异常的参数的实际值。
     */
    public Object getActualValue()
    {
        return actualValue;
    }
}
