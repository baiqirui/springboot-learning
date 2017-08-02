package com.mealkey.core.util;

import com.mealkey.core.model.CommonObject;


/**
 *  存储系统公共值对象
 * 
 * @author  mealkey
 * @version  [版本号, 2017年3月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonObjectHolder
{
    private static final ThreadLocal<CommonObject> threadLocal = new ThreadLocal<CommonObject>();
    
    public static CommonObject get()
    {
        return threadLocal.get();
    }
    
    public static void set(CommonObject value)
    {
        threadLocal.set(value);
    }
    
    public static void remove()
    {
        CommonObject commonObject = threadLocal.get();
        if (null != commonObject)
        {
            threadLocal.remove();
        }
    }
    
    public static String getSessionId()
    {
        return (null == threadLocal.get()) ?  null : threadLocal.get().getSessionId();
    }
    
    public static Long getTenantId()
    {
        return (null == threadLocal.get()) ?  null : threadLocal.get().getTenantId();
    }
    
    public static Long getStoreId()
    {
        return (null == threadLocal.get()) ?  null : threadLocal.get().getStoreId();
    }
    
    public static Long getUserId()
    {
        return (null == threadLocal.get()) ?  null : threadLocal.get().getUserId();
    }
    
}
