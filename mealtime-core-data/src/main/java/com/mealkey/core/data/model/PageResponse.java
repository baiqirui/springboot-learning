package com.mealkey.core.data.model;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageResponse<T>
{

    /**
     * 符合查询条件的总页数。
     */
    private Integer pages;

    // 数据集合
    private List<T> result;

    public PageResponse(List<T> list)
    {
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        this.pages = pageInfo.getPages();
        this.result = pageInfo.getList();
    }

    public PageResponse(int pages, List<T> list)
    {
        this.pages = pages;
        this.result = list;
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

    public void setResult(List<T> result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "{pages:" + pages + ", result:" + result + "}";
    }

}
