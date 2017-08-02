package com.mealkey.core.data.model;

import java.io.Serializable;

/**
 * 分页的响应结果。
 * @author: yidi
 */
public class PagingResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 符合查询条件的总页数。
     */
    private Integer pages;
    
    /**
     * 当次请求的结果数据。
     */
    private Object result;
    
    public PagingResponse(Integer pages, Object result)
    {
        this.pages = pages;
        this.result = result;
    }

    public Integer getPages()
    {
        return pages;
    }

    public void setPages(Integer pages)
    {
        this.pages = pages;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }
}
