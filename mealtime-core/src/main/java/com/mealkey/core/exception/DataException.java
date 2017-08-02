package com.mealkey.core.exception;

/**
 * 在数据操作出错时引发的异常。
 * @author: yidi
 */
public class DataException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    public DataException(String message)
    {
        super(message);
    }
}
