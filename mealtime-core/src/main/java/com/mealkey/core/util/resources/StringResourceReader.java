package com.mealkey.core.util.resources;


import com.mealkey.core.util.StringUtil;
import com.mealkey.core.exception.ArgumentNullException;

import java.io.IOException;
import java.io.InputStream;

/**
 * 实现字符串资源文件读取器。
 * @author: 
 * 2013-1-25，yidi。
 */
public class StringResourceReader
{
    private static final char NameValueSeparatorChar = '=';
    
    private java.io.BufferedReader reader;
    
    private String name;
    
    private Object value;

    /**
     * 为指定的流初始化 ResourceReader 类的新实例。
     * @param stream 用于读取资源的输入流。
     */
    public StringResourceReader(InputStream stream)
    {
        ArgumentNullException.checkNull("stream",stream);
        reader = createTextReader(stream,"utf-8",1024);
    }

    /** 
     * 释放读取过程中占用的任何资源。
     */
    public void close()
    {
        try
        {
            reader.close();
        }
        catch (java.io.IOException e)
        {
            try {
                throw new IOException(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /** 
     * 从流中读取下一个资源。当第一次创建和初始化读取器时，没有可用的信息。
     * 必须调用 read 读取第一个资源。
     * @return 如果成功读取了下一个资源，则为 true；如果没有其他资源可读取，
     * 则为 false。
     */
    public boolean read()
    {
        String line = null;
        do
        {
            try
            {
                line = reader.readLine();
            }
            catch (java.io.IOException e)
            {
                try {
                    throw new IOException(e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (null == line)
            {
                return false;
            }
        }
        while (line.length() == 0);

        int index = line.indexOf(NameValueSeparatorChar);
        if (-1 == index)
        {
            throw new ResourceException(line);
        }
        name = StringUtil.substring(line,0,index);
        value = StringUtil.substring(line,index + 1);
        return true;
    }
    
    /** 
     * 获取当前资源的名称。
     * @return 当前资源的名称。
     */
    public String getName()
    {
        return name;
    }
    
    /** 
     * 获取当前资源的值。
     * @return 当前资源的值。
     */
    public Object getValue()
    {
        return value;
    }
    
    /** 
     * 创建语言内部的输入流读取器。
     * @param in 要读取的流。
     * @param encoding 要使用的字符编码。
     * @param bufferSize 缓冲区大小。
     * @return 语言内部的输入流读取器。
     */
    private static java.io.BufferedReader createTextReader
        (InputStream in, String encoding, int bufferSize)
    {
        try
        {
            return new java.io.BufferedReader(new java.io.InputStreamReader
                (in,encoding),bufferSize);
        }
        catch (NullPointerException e)
        {
            throw new ArgumentNullException("in");
        }
        catch (Exception e)
        {
            try {
                throw new IOException(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
