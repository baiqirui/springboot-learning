package com.mealkey.core.util;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import com.mealkey.core.BooleanReference;

/**
 * 为 32 位整数提供常数和静态方法。
 * @author yidi
 */
public final class IntegerUtil
{
    /**
     * 将数字的字符串表示形式转换为它的等效 32 位整数。该转换不抛出异常。
     * <p>该字符串使用当前区域性特定格式设置信息。
     * @param s 包含要转换的数字的字符串。
     * @param isSuccess 指示解析是否成功。
     * @return s 中包含的数字的等效 32 位整数。
     */
    public static int parse(String s, BooleanReference isSuccess)
    {
        ParsePosition parsePosition = new ParsePosition(0);
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        Number result = numberFormat.parse(s,parsePosition);
        isSuccess.setValue(-1 == parsePosition.getErrorIndex());
        return null != result ? result.intValue() : 0;
    }
    
    public static void main(String[] args) throws Exception
    {
        BooleanReference a = new BooleanReference();
        int b = IntegerUtil.parse("a21233123",a);
        System.out.print(a.getValue()+" "+b);
    }
}
