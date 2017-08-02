package com.mealkey.core.model.reflection;


import com.mealkey.core.exception.ExceptionBase;
import com.mealkey.core.util.resources.ResourceUtil;

public class TargetInvocationException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;

    public TargetInvocationException(Throwable langException)
    {
        super(ResourceUtil.getString(TargetInvocationException.class,
            "TargetInvocation"),langException);
    }
}
