package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 当方法调用对于对象的当前状态无效时引发的异常。
 * @author yidi
 */
public class InvalidOperationException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化 InvalidOperationException 类的新实例。
     */
    public InvalidOperationException()
    {
        this(ResourceUtil.getString(InvalidOperationException.class,
            "InvalidOperation"));
    }
    
    /**
     * 初始化 InvalidOperationException 类的新实例，并指定错误信息。
     * @param message 解释异常原因的错误信息。
     */
    public InvalidOperationException(String message)
    {
        super(message);
    }
    
    /** 
     * 创建 InvalidOperationException 异常，并指定导致异常的原因为
     * “集合中元素的 key 或者 parentKey 已修改，导致树结构损坏”。
     */
    public static InvalidOperationException createTreeFailedVersion()
    {
        return new InvalidOperationException(ResourceUtil.getString
            (InvalidOperationException.class,
            "InvalidOperation_TreeFailedVersion"));
    }
}
