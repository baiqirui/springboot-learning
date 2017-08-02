package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;


/**
 * 发生 API 签名错误时引发的异常。
 * @author yidi
 */
public class SignatureException extends BusinessException
{
    private static final long serialVersionUID = 1L;

    public SignatureException()
    {
        this(ResourceUtil.getString(SignatureException.class,"Signature"));
    }
    
    public SignatureException(String message)
    {
        super(message);
    }
}
