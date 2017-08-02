package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 当调用的方法不受支持时引发的异常。
 * @author yidi
 */
public class NotSupportedException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化 NotSupportedException 类的新实例 。
     */
    public NotSupportedException()
    {
        this(ResourceUtil.getString(NotSupportedException.class,
            "NotSupported"));
    }

    /**
     * 初始化 NotSupportedException 类的新实例，并指定错误信息。
     * @param message 解释异常原因的错误信息。
     */
    public NotSupportedException(String message)
    {
        super(message);
    }
}
