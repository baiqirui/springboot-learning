package com.mealkey.core.model.reflection.probe;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mealkey.core.model.reflection.MissingMemberException;
import com.mealkey.core.util.StringUtil;

/**
 * 提供访问类型的成员的功能。
 * @author: yidi
 */
public class ProbeInfo
{
    private static final String GetterPrefix = "get";
    
    private static final String SetterPrefix = "set";
    
    private static final int GetterPrefixLength = GetterPrefix.length();
    
    private static final int SetterPrefixLength = SetterPrefix.length();
    
    private static final HashMap<Class<?>,ProbeInfo> instanceFromType 
        = new HashMap<Class<?>,ProbeInfo>();
    
    private Class<?> type;
    
    private HashMap<String,ProbeMember> members;
    
    public ProbeInfo(Class<?> type)
    {
        this.type = type;
        internalGetMembers();
    }
    
    /**
     * 获取指定类型的 ProbeInfo。
     * @param type 类型。
     * @return 指定类型的 ProbeInfo。
     */
    public static ProbeInfo getInstance(Class<?> type)
    {
        ProbeInfo probeInfo = instanceFromType.get(type);
        if (null == probeInfo)
        {
            probeInfo = new ProbeInfo(type);
            instanceFromType.put(type,probeInfo);
        }
        return probeInfo;
    }
    
    /**
     * 获取指定名称的成员。
     * @param name 名称。
     * @return 具有指定名称的成员。
     */
    public ProbeMember getMember(String name)
    {
        BooleanReference isSuccess = new BooleanReference();
        ProbeMember member = getMember(name,isSuccess);
        if (isSuccess.getValue())
        {
            return member;
        }
        throw new MissingMemberException(name);
    }
    
    /**
     * 获取指定名称的成员。该获取不抛出异常。
     * @param name 名称。
     * @param isSuccess 指示获取是否成功。
     * @return 具有指定名称的成员。
     */
    public ProbeMember getMember(String name, BooleanReference isSuccess)
    {
        ProbeMember member = members.get(name);
        if (null != member)
        {
            isSuccess.setValue(true);
            return member;
        }
        isSuccess.setValue(false);
        return null;
    }

    /**
     * 返回可循环访问成员集合的迭代器。
     * @return 可循环访问成员集合的 MapIterator。
     */
    public Iterator<Map.Entry<String,ProbeMember>> getMemberIterator()
    {
        return members.entrySet().iterator();
    }
    
    private void internalGetMembers()
    {   
        //计算 getter 及 setter
        Method[] methods = type.getMethods();
        ArrayList<Method> getters = new ArrayList<Method>();
        HashMap<String,Method> setters = new HashMap<String,Method>();
        members = new HashMap<String,ProbeMember>();
        for (int i = 0; i < methods.length; i++)
        {
            Method method = methods[i];
            String methodName = method.getName();
            if (!Modifier.isStatic(method.getModifiers()) 
                && (method.getDeclaringClass() != Object.class))
            {
                if ((0 == method.getParameters().length) 
                    && (method.getReturnType() != void.class)
                    && methodName.startsWith(GetterPrefix) 
                    && (methodName.length() > GetterPrefixLength))
                {
                    if (!method.isBridge())
                    {
                        getters.add(method);
                    }
                }
                else if ((1 == method.getParameters().length) 
                    && (method.getReturnType() == void.class) 
                    && (methodName.startsWith(SetterPrefix) 
                    && (methodName.length() > SetterPrefixLength)))
                {
                    setters.put(methodName,method);
                }
            }
        }
        
        //计算 Event or Property
        int getterCount = getters.size();
        for (int i = 0; i < getterCount; i++)
        {
            Method getter = getters.get(i);
            Class<?> memberType = getter.getReturnType();
            String memberName = StringUtil.substring
                (getter.getName(),GetterPrefix.length());
            Method setter = setters.get(SetterPrefix + memberName);
            if ((null != setter) || (!TypeUtil.isStringable(memberType)
                && !memberType.isArray()))
            {
                Property property = new Property(NameUtil.toCamel(memberName),
                    getter,setter);
                members.put(property.getName(),property);
            }
        }
    }
}
