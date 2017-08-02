package com.mealkey.core.response;

import java.io.Serializable;

/**
 * 错误结果。
 * @author: yidi
 */
public class ErrorResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码（默认值0）
     */
    private Integer code;
    
    /**
     * 错误消息
     */
    private String error;
    
    public ErrorResponse()
    {
    }
    
    public ErrorResponse(Integer code, String error)
    {
        this.code = code;
        this.error = error;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
