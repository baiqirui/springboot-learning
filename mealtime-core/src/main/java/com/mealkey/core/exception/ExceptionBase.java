package com.mealkey.core.exception;

/**
 * 表示在应用程序执行过程中发生的错误。
 * @author yidi
 */
public abstract class ExceptionBase extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ExceptionBase(String message)
    {
        super(message);
    }
    
    public ExceptionBase(Throwable cause)
    {
        super(cause);
    }

    public ExceptionBase(String message, Throwable cause)
    {
        super(message,cause);
    }
}
