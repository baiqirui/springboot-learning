package com.mealkey.core.exception;


import com.mealkey.core.util.resources.ResourceUtil;
import com.mealkey.core.util.StringUtil;

/**
 * 在向方法提供的其中一个参数无效时引发的异常。
 * @author yidi
 */
public class ArgumentException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;
    
    private String argumentName;

    /**
     * 初始化 ArgumentException 类的新实例，并指定导致异常的参数的名称。
     * @param argumentName 导致当前异常的参数的名称。
     */
    public ArgumentException(String argumentName) 
    {
        this(ResourceUtil.getString(ArgumentException.class,"Argument"),
            argumentName);
    }

    /**
     * 初始化 ArgumentException 类的新实例，并指定错误信息
     * 和导致异常的参数的名称。
     * @param message 解释异常原因的错误信息。
     * @param argumentName 导致异常的参数的名称。
     */
    public ArgumentException(String message, String argumentName) 
    {
        super(message);
        this.argumentName = argumentName;
    }
    
    /**
     * 创建 ArgumentException 异常，并指定导致异常的原因为
     * “目标数组的长度不足”。
     * @param argumentName 参数的名称。
     * @return ArgumentException 异常。
     */
    public static ArgumentException createArrayTooSmall(String argumentName)
    {
        return new ArgumentException(ResourceUtil.getString
            (ArgumentException.class,"Argument_ArrayTooSmall"),
            argumentName);
    }
    
    /**
     * 检测参数值是否为空引用、空字符串或者只包含空白字符的字符串。
     * @param argumentName 参数的名称。
     * @param argumentValue 参数的实际值。
     */
    public static void checkNullOrBlankString(String argumentName, 
        String argumentValue)
    {
        if ((null == argumentValue) || (0 == argumentValue.length()) 
            || (0 == argumentValue.trim().length())) 
        {
            throw createNullOrBlankString(argumentName);
        }
    }
    
    /**
     * 检测参数值是否为空引用或空字符串。
     * @param argumentName 参数的名称。
     * @param argumentValue 参数的实际值。
     */
    public static void checkNullOrEmptyString(String argumentName, 
        String argumentValue)
    {
        if ((null == argumentValue) || (0 == argumentValue.length())) 
        {
            throw createNullOrEmptyString(argumentName);
        }
    }
    
    /** 
     * 创建 ArgumentException 异常，并指定导致异常的原因为
     * “指定名称的值已经属于此对象”。
     * @param argumentName 参数的名称。
     * @param name 名称。
     * @param objType 对象的类型。
     * @return ArgumentException 异常。
     */
    public static ArgumentException createDuplicateName
        (String argumentName, String name, Class<?> objType)
    {
        return new ArgumentException(ResourceUtil.getString
            (ArgumentException.class,"Argument_DuplicateName",
            new String[] {name,objType.getName()}),
            argumentName);
    }
    
    /** 
     * 创建 ArgumentException 异常，并指定导致异常的原因为
     * “值不属于此对象”。
     * @param argumentName 参数的名称。
     * @param objType 对象的类型。
     * @return ArgumentException 异常。
     */
    public static ArgumentException createNotIn(String argumentName, 
        Class<?> objType)
    {
        return new ArgumentException(ResourceUtil.getString
            (ArgumentException.class,"Argument_NotIn",
            new String[] {objType.getName()}),argumentName);
    }
    
    /**
     * 创建 ArgumentException 异常，并指定导致异常的原因为
     * “值不能是空引用、空字符串或者只包含空白字符的字符串”。
     * @param argumentName 参数的名称。
     * @param argumentValue 参数的实际值。
     */
    public static ArgumentException createNullOrBlankString
        (String argumentName)
    {
        throw new ArgumentException(ResourceUtil.getString
            (ArgumentException.class,"Argument_NullOrBlankString"),
            argumentName);
    }
    
    /**
     * 创建 ArgumentException 异常，并指定导致异常的原因为
     * “值不能是空引用或空字符串”。
     * @param argumentName 参数的名称。
     * @return ArgumentException 异常。
     */
    public static ArgumentException createNullOrEmptyString
        (String argumentName)
    {
        return new ArgumentException(ResourceUtil.getString
            (ArgumentException.class,"Argument_NullOrEmptyString"),
            argumentName);
    }
    
    /**
     * 获取错误信息和参数名；如果未设置参数名，则仅获取错误信息。
     * @return 描述异常的详细信息的文本字符串。
     */
    @Override
    public String getMessage()
    {
        if (!StringUtil.isNullOrEmpty(argumentName))
        {
            return super.getMessage() + Environment.NewLine 
                + ResourceUtil.getString(ArgumentException.class,
                "Argument_Name",new Object[] {argumentName});
        }
        return super.getMessage();
    }

    /** 
     * 获取导致异常的参数的名称。
     * @return 导致异常的参数的名称。
     */
    public String getArgumentName()
    {
        return argumentName;
    }
}
