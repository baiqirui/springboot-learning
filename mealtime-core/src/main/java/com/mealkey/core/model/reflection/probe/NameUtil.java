package com.mealkey.core.model.reflection.probe;


import com.mealkey.core.exception.ArgumentException;
import com.mealkey.core.util.StringUtil;

/**
 * 为名称提供常数和静态方法。
 * @author yidi
 */
public final class NameUtil
{
    /**
     * 将指定名称转换为 Camel 大小写形式。
     * <p>Camel 大小写的第一个字符是小写的，任何后面连接单词的第一个
     * 字母是大写的。
     * @param name 要转换的名称。
     * @return Camel 大小写形式。
     */
    public static String toCamel(String name)
    {
        ArgumentException.checkNullOrEmptyString("name",name);
        return Character.toLowerCase(name.charAt(0)) 
            + StringUtil.substring(name,1,name.length() - 1);
    }

    /**
     * 将指定名称转换为 Pascal 大小写形式。
     * <p>Pascel 大小写的第一个字符是大写的。
     * @param name 要转换的名称。
     * @return Pascal 大小写形式。
     */
    public static String toPascal(String name)
    {
        ArgumentException.checkNullOrEmptyString("name",name);
        return Character.toUpperCase(name.charAt(0)) 
            + StringUtil.substring(name,1,name.length() - 1);
    }
    
    /**
     * 获取作为名称后缀的索引。
     * @param name 名称。
     * @return 如果 name 以数字结尾，则为该连续数字；否则为 -1。
     */
    public static int getSuffixIndex(String name)
    {
        ArgumentException.checkNullOrEmptyString("name",name);
        int start = name.length() - 1;
        for (; start != -1; start--)
        {
            char ch = name.charAt(start);
            if ((ch < '0') || (ch > '9'))
            {
                break;
            }
        }
        if (start == name.length() - 1)
        {
            return -1;
        }
        int index = 0;
        for (int i = start + 1; i < name.length(); i++)
        {
            index = index * 10 + (name.charAt(i) - '0');
        }
        return index;
    }
}
