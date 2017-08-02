package com.mealkey.core.model.reflection.probe;


import com.mealkey.core.model.reflection.TargetInvocationException;

import java.util.Hashtable;

/**
 * 为类型提供常数和静态方法。
 * @author yidi
 */
public class TypeUtil
{
    private static TypeStaticFields staticFields 
        = new TypeStaticFields();
    
    public static boolean isPrimitive(Class<?> clazz)
    {
        if ((clazz == boolean.class) || (clazz == Boolean.class) 
            || (clazz == char.class) || (clazz == Character.class) 
            || (clazz == byte.class) || (clazz == Byte.class) 
            || (clazz == short.class) || (clazz == Short.class) 
            || (clazz == int.class) || (clazz == Integer.class) 
            || (clazz == long.class) || (clazz == Long.class) 
            || (clazz == float.class) || (clazz == Float.class) 
            || (clazz == double.class) || (clazz == Double.class))
        {
            return true;
        }
        return false;
    }

    public static boolean isStringable(Class<?> clazz)
    {
        if (isPrimitive(clazz) || (clazz == String.class) 
            || (clazz == Class.class) || clazz.isEnum() 
            || (clazz.isArray() && isStringable(clazz.getComponentType())))
        {
            return true;
        }
        return false;
    }
    
    public static ProbeInfo getCachedProbeInfo(Class<?> clazz)
    {
        ProbeInfo probeInfo = staticFields.probeInfoFromType.get(clazz);
        if (probeInfo == null)
        {
            probeInfo = new ProbeInfo(clazz);
            staticFields.probeInfoFromType.put(clazz,probeInfo);
        }
        return probeInfo;
    }
    
    public static <T> T newInstance(Class<T> clazz)
    {
        try
        {
            return clazz.newInstance();
        }
        catch (Exception e)
        {
            throw new TargetInvocationException
                (e.getCause() != null ? e.getCause() : e);
        }
    }
    
    private static class TypeStaticFields
    {   
        Hashtable<Class<?>,ProbeInfo> probeInfoFromType;

        TypeStaticFields()
        {
            probeInfoFromType = new Hashtable<Class<?>,ProbeInfo>();
        }
    }
}
