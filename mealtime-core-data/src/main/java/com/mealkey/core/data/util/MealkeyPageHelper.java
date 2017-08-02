package com.mealkey.core.data.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class MealkeyPageHelper
{
    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public  static <E> Page<E> startPage(int pageNum, int pageSize) 
    {
        //为了兼容老的分页，开始页数为0，此处+1
        pageNum = pageNum + 1;
        return PageHelper.startPage(pageNum, pageSize, true);
    }
}
