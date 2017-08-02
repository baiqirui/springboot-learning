package com.mealkey.core.exception;

import com.mealkey.core.util.resources.ResourceUtil;

/**
 * 已存在指定数据时引发的异常。
 * @author: yidi
 */
public class DuplicateException extends BusinessException
{   
    private static final long serialVersionUID = 1L;
    
    /**
     * 初始化 DuplicateException 类的新实例。
     */
    public DuplicateException()
    {
        super(ResourceUtil.getString(DuplicateException.class,"Duplicate"));
    }

    /**
     * 初始化 DuplicateException 类的新实例。
     * @param message 解释异常原因的错误信息。
     */
    public DuplicateException(String message)
    {
        super(message);
    }
    
    /**
     * 创建 ObjectNotFoundException 异常，并指定导致异常的原因为
     * “已存在指定业务唯一键”。
     */
    public static DuplicateException createUniqueKey(String keyName)
    {
        return new DuplicateException(ResourceUtil.getString
            (DuplicateException.class,"Duplicate_UniqueKey",
            new String[] {keyName}));
    }
}
