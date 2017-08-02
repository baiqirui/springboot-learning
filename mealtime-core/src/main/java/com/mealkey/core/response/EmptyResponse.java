package com.mealkey.core.response;

import java.io.Serializable;

/**
 * 空响应结果。
 * @author yidi
 */
public class EmptyResponse implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public static final EmptyResponse instance = new EmptyResponse();
    
    private int success; 
    
    /**
     * 获取唯一实例。
     */
    public static EmptyResponse getInstance()
    {
        return instance;
    }
    
    protected EmptyResponse()
    {
        success = 1;
    }

    public int getSuccess()
    {
        return success;
    }

    public void setSuccess(int success)
    {
        this.success = success;
    }
}
