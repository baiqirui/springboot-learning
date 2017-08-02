package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 未找到指定数据时引发的异常。
 * @author: yidi
 */
public class NotFoundException extends BusinessException
{   
    private static final long serialVersionUID = 1L;
    
    /**
     * 初始化 NotFoundException 类的新实例。
     */
    public NotFoundException()
    {
        super(ResourceUtil.getString(NotFoundException.class,"NotFound"));
    }

    /**
     * 初始化 NotFoundException 类的新实例。
     * @param message 解释异常原因的错误信息。
     */
    public NotFoundException(String message)
    {
        super(message);
    }
    
    /**
     * 创建 NotFoundException 异常，并指定导致异常的原因为
     * “未找到指定对象”。
     */
    public static NotFoundException createMissingObject(String objectName)
    {
        return new NotFoundException(ResourceUtil.getString
            (NotFoundException.class,"NotFound_MissingObject",
            new String[] {objectName}));
    }
}
