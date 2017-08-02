package com.mealkey.core.model.reflection.probe;

/**
 * 定义 {@link ProbeInfo} 的成员。
 */
public interface ProbeMember
{
    /**
     * 通过类实现时，获取该成员的名称。
     * @return 该成员的名称。
     */
    String getName();
    
    /**
     * 通过类实现时，获取成员的类型。
     * @return 成员的类型。
     */
    Class<?> getMemberType();
    
    /**
     * 通过类实现时，获取成员的泛型类型。
     * @return 成员的泛型类型。
     */
    java.lang.reflect.Type getGenericMemberType();
    
    /**
     * 通过类实现时，获取指定对象上该成员的值。
     * @param obj 指定对象。
     * @return 指定对象上该成员的值。
     */
    Object getValue(Object obj);
    
    /**
     * 通过类实现时，设置指定对象上该成员的值。
     * @param obj 指定对象。
     * @param value 值。
     */
    void setValue(Object obj, Object value);
}
