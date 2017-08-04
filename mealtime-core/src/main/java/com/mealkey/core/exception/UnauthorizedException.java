package com.mealkey.core.exception;

/**
 * 表示用户未授权异常。
 * @author: yidi
 */
public class UnauthorizedException extends BusinessException
{
    public UnauthorizedException(int errorCode)
    {
        super(errorCode);
    }

    private static final long serialVersionUID = 1L;

}
