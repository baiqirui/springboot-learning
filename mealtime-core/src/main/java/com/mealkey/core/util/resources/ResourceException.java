package com.mealkey.core.util.resources;


import com.mealkey.core.exception.ExceptionBase;

/**
 * 在访问资源时引发的异常。
 * @author: 
 * 2012-9-13，yd。
 */
public class ResourceException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化 ResourceException 类的新实例。
     */
    public ResourceException()
    {
        super(ResourceUtil.getString(ResourceException.class,"Resource"));
    }
    
    /**
     * 初始化 ResourceException 类的新实例，并指定错误信息。
     * @param message 解释异常原因的错误信息。
     */
    public ResourceException(String message)
    {
        super(message);
    }
}
