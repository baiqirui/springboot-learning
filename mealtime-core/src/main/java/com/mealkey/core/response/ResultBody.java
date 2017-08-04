package com.mealkey.core.response;

import java.io.Serializable;

import com.mealkey.core.constant.ResultCodeConstant;
import com.mealkey.core.util.resources.PropertiesListenerConfig;

/**
 * 通用返回结果
 * 
 * @author baiqirui
 * @version [版本号, 2017年8月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResultBody implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private int code;
    
    private String message;
    
    private Object data;
    
    public ResultBody()
    {
        super();
    }
    
    public ResultBody(Object data)
    {
        this(ResultCodeConstant.SUCCESS, PropertiesListenerConfig.getResultMessage(ResultCodeConstant.SUCCESS), data);
    }
    
    public ResultBody(int code, String message)
    {
        super();
        this.code = code;
        this.message = message;
    }
    
    public ResultBody(int code, String message, Object data)
    {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
    public static ResultBody createUnKnowExceptionResultBody()
    {
        return new ResultBody(ResultCodeConstant.UNKONW_EXCEPTION,
            PropertiesListenerConfig.getResultMessage(ResultCodeConstant.UNKONW_EXCEPTION));
    }
}
