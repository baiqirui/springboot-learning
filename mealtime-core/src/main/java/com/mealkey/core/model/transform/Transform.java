package com.mealkey.core.model.transform;

import java.util.List;

/**
 * 定义数据格式转换功能。
 * @author: yidi
 * @param S 源对象类型。
 * @param D 目标对象类型。
 */
public interface Transform<S,D>
{
    List<D> transform(List<S> list);
}
