package com.mealkey.core.util.resources;

import com.mealkey.core.util.StringUtil;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * 提供访问资源的静态方法。
 * @author yidi
 */
public final class ResourceUtil
{
    private static final String BaseName = "string.properties";
    
    private static final int PrefixLength = "com.mealkey.".length();
    
    private static Map<String,Map<String,String>> rmFromPackage;
    
    /**
     * 获取指定名称的字符串资源。
     * @param type 类型。
     * @param name 资源名称。
     * @return 指定名称的当前区域的字符串资源。
     */
    public static String getString(Class<?> type, String name)
    {
        Map<String,String> resourceSet = getResourceSet(type);
        return resourceSet.get(name);
    }

    /**
     * 获取指定名称的字符串资源，并将其中的格式项替换为指定数组中相应 
     * Object 实例的值的文本等效项。
     * @param type 类型。
     * @param name 资源名称。
     * @param values 包含零个或多个要格式化的对象的 Object 数组。
     * @return 指定名称的当前区域的字符串资源，其中格式项已替换为 values 中相应 
     * Object 实例的字符串等效项。
     */
    public static String getString(Class<?> type, String name, 
        Object[] values)
    {
        String format = getString(type,name);
        return StringUtil.format(format,values);
    }
    
    /**
     * 获取用于指定类型的访问资源的 Properties。
     * @param type 类型。
     * @return 访问资源的 Properties。
     */
    private static Map<String,String> getResourceSet(Class<?> type)
    {
        String pkg = type.getName();
        pkg = pkg.substring(0,pkg.indexOf('.',PrefixLength));
        if (null == rmFromPackage)
        {
            rmFromPackage = new Hashtable<String,Map<String,String>>();
        }
        Map<String,String> resourceSet = rmFromPackage.get(pkg);
        if (null == resourceSet)
        {
            String fileName = "/" + pkg.replace('.','/') + "/" + BaseName;
            InputStream in = type.getResourceAsStream(fileName);
            if (null == in)
            {
                System.out.println(fileName);
                return null;
            }
            resourceSet = new Hashtable<String,String>();
            StringResourceReader reader = new StringResourceReader(in);
            while (reader.read())
            {
                resourceSet.put(reader.getName(),(String)reader.getValue());
            }
            reader.close();
            rmFromPackage.put(pkg,resourceSet);
        }
        return resourceSet;
    }
}
