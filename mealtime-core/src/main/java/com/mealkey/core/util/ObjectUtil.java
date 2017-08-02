package com.mealkey.core.util;


import com.mealkey.core.exception.ArgumentNullException;
import com.mealkey.core.model.reflection.probe.MapProbe;
import com.mealkey.core.model.reflection.probe.ObjectProbe;
import com.mealkey.core.model.reflection.probe.SimpleObjectProbe;

import java.util.Map;

/**
 * 为对象提供常数和静态方法。
 * @author yidi
 */
public final class ObjectUtil
{
    /**
     * 获取指定对象的 ObjectProbe。
     * @param obj 对象。
     * @return 指定对象的 ObjectProbe。
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public static ObjectProbe getProbe(Object obj)
    {
        ArgumentNullException.checkNull("obj",obj);
        if (obj instanceof ObjectProbe)
        {
            return (ObjectProbe)obj;
        }
        if (obj instanceof Map)
        {
            return new MapProbe((Map<String,?>)obj);
        }
        return new SimpleObjectProbe(obj);
    }
    
    /**
     * 确定指定的 Object 实例是否被视为相等。
     * @param obj1 要比较的第一个 Object。
     * @param obj2 要比较的第二个 Object。
     * 如果 obj1 是与 obj2 相同的实例，或者如果两者均为空引用，
     * @return 或者如果两者被视为相等，则为 true；否则为 false。
     */
    public static boolean equals(Object obj1, Object obj2)
    {
        if (obj1 == obj2)
        {
            return true;
        }
        if ((null != obj1) && (null != obj2))
        {
            return obj1.equals(obj2);
        }
        return false;
    }
}
