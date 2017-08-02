package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 发生业务错误时引发的异常。
 * @author yidi
 */
public class BusinessException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    public BusinessException() 
    {
        this(ResourceUtil.getString(ArgumentException.class,"Business"));
    }
    
    public BusinessException(String message)
    {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
