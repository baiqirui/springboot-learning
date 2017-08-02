package com.mealkey.core.model.reflection.probe;


import com.mealkey.core.exception.InvalidOperationException;
import com.mealkey.core.exception.TargetInvocationException;
import com.mealkey.core.model.reflection.Convert;

import java.lang.reflect.Method;

/**
 * 提供对类型的属性成员的访问。
 * @author yidi
 */
public class Property implements ProbeMember
{
    private String name;
    
    private Method getter;
    
    private Method setter;
    
    Property(String name, Method getter, Method setter)
    {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
    }

    public String getName()
    {
        return name;
    }
    
    public Class<?> getMemberType()
    {
        return getter.getReturnType();
    }
    
    public java.lang.reflect.Type getGenericMemberType()
    {
        return getter.getGenericReturnType();
    }
    
    public Object getValue(Object obj)
    {
        try
        {
            return getter.invoke(obj,new Object[] {});
        }
        catch (Exception e)
        {
            throw new TargetInvocationException
                (null != e.getCause() ? e.getCause() : e);
        }
    }
    
    public void setValue(Object obj, Object value)
    {
        if (null == setter)
        {
            throw new InvalidOperationException();
        }
        try
        {
            setter.invoke(obj,new Object[] {null != value ? Convert
                .changeType((String)value,getter.getReturnType()) : null});
        }
        catch (Exception e)
        {
            throw new TargetInvocationException
                (null != e.getCause() ? e.getCause() : e);
        }
    }
}
