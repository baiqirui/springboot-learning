package com.mealkey.core.exception;

/**
 * 表示用户未授权异常。
 * @author: yidi
 */
public class UnauthorizedException extends BusinessException
{
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public UnauthorizedException(String message)
    {
        super(message);
    }
}
