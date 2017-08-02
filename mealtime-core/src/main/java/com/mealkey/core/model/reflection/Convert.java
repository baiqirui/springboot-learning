package com.mealkey.core.model.reflection;

import com.mealkey.core.exception.ArgumentException;
import com.mealkey.core.exception.ArgumentNullException;
import com.mealkey.core.exception.ArgumentOutOfRangeException;
import com.mealkey.core.exception.InvalidOperationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将一个基本数据类型转换为另一个基本数据类型。
 * @author: yidi。
 */
public class Convert
{
    /**
     * 将指定的字符串转换为等效的指定类型的对象。
     * @param value 字符串。
     * @return 等效于 value 的指定类型的对象。
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public static Object changeType(String value, Class<?> type)
    {
        if ((null != value) && (value.getClass() == type))
        {
            return value;
        }
        if ((type == boolean.class) || (type == Boolean.class))
        {
            if ("1".equals(value))
            {
                return true;
            }
            return Boolean.parseBoolean(value);
        }
        if ((type == char.class) || (type == Character.class))
        {
            return value.charAt(0);
        }
        if ((type == byte.class) || (type == Byte.class))
        {
            return Byte.parseByte(value);
        }
        if ((type == short.class) || (type == Short.class))
        {
            return Short.parseShort(value);
        }
        if ((type == int.class) || (type == Integer.class))
        {
            return Integer.parseInt(value);
        }
        if ((type == long.class) || (type == Long.class))
        {
            return Long.parseLong(value);
        }
        if ((type == float.class) || (type == Float.class))
        {
            return Float.parseFloat(value);
        }
        if ((type == double.class) || (type == Double.class))
        {
            return Double.parseDouble(value);
        }
        if (type == String.class)
        {
            return value;
        }
        if (type == Class.class)
        {
            try
            {
                return Class.forName(value);
            }
            catch (ClassNotFoundException e)
            {
                throw new InvalidOperationException(e.getMessage());
            }
        }
        else if (type.isEnum())
        {
            return Enum.valueOf((Class<Enum>)type,(String)value);
        }
        else if (Date.class.isAssignableFrom(type))
        {
            try
            {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse
                    (value);
            }
            catch (ParseException e)
            {
                throw new InvalidOperationException(type.getName());
            }
        }
        throw new InvalidOperationException(type.getName());
    }
    
    /** 
     * 将指定的 32 位整数转换为字节数组表示形式。
     * @param value 32 位整数。
     * @return 长度为 4 的字节数组。
     */
    public static byte[] toBytes(int value)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(value & 0xff);
        bytes[1] = (byte)((value >>> 8) & 0xff);
        bytes[2] = (byte)((value >>> 16) & 0xff);
        bytes[3] = (byte)((value >>> 24) & 0xff);
        return bytes;
    }
    
    /**
     * 将指定的 64 位整数转换为字节数组表示形式。
     * @param value 64 位整数。
     * @return 长度为 8 的字节数组。
     */
    public static byte[] toBytes(long value)
    {
        byte[] bytes = new byte[8];
        int lowInt = (int)(value & 0xffffffff);
        int highInt = (int)((value >>> 32) & 0xffffffff);
        bytes[0] = (byte)(lowInt & 0xff);
        bytes[1] = (byte)((lowInt >>> 8) & 0xff);
        bytes[2] = (byte)((lowInt >>> 16) & 0xff);
        bytes[3] = (byte)((lowInt >>> 24) & 0xff);
        bytes[4] = (byte)(highInt & 0xff);
        bytes[5] = (byte)((highInt >>> 8) & 0xff);
        bytes[6] = (byte)((highInt >>> 16) & 0xff);
        bytes[7] = (byte)((highInt >>> 24) & 0xff);
        return bytes;
    }
    
    /** 
     * 返回由字节数组中指定位置的 4 个字节转换来的 32 位整数。
     * @param bytes 字节数组。
     * @param index bytes 内的起始索引。
     * @return 由 4 个字节构成、从 index 开始的 32 位整数。
     */
    public static int toInteger(byte[] bytes, int index)
    {
        ArgumentNullException.checkNull("bytes",bytes);
        if ((index < 0) || (index >= bytes.length))
        {
            throw ArgumentOutOfRangeException.createIndex("index",index);
        }
        if (index + 4 > bytes.length)
        {
            throw new ArgumentException("bytes");
        }
        return ((bytes[index] & 0xff) | ((bytes[index + 1] & 0xff) << 8) 
            | ((bytes[index + 2] & 0xff) << 16) 
            | ((bytes[index + 3] & 0xff )<< 24));
    }
    
    /**
     * 返回由字节数组中指定位置的 8 个字节转换来的 64 位整数。
     * @param bytes 字节数组。
     * @param index bytes 内的起始索引。
     * @return 由 8 个字节构成、从 index 开始的 64 位整数。
     */
    public static long toLong(byte[] bytes, int index)
    {
        ArgumentNullException.checkNull("bytes",bytes);
        if ((index < 0) || (index >= bytes.length))
        {
            throw ArgumentOutOfRangeException.createIndex("index",index);
        }
        if (index + 8 > bytes.length)
        {
            throw ArgumentException.createArrayTooSmall("bytes");
        }
        int lowInt = (bytes[index] & 0xff) 
            | ((bytes[index + 1] & 0xff) << 8) 
            | ((bytes[index + 2] & 0xff) << 16) 
            | ((bytes[index + 3] & 0xff) << 24);
        int hightInt = (bytes[index + 4] & 0xff) 
            | ((bytes[index + 5] & 0xff) << 8) 
            | ((bytes[index + 6] & 0xff) << 16) 
            | ((bytes[index + 7] & 0xff) << 24);
        return (0x00000000ffffffffL & lowInt) 
            | ((0x00000000ffffffffL & hightInt) << 32);
    }
}
