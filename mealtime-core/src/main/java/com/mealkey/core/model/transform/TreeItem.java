package com.mealkey.core.model.transform;

import java.util.ArrayList;

/**
 * 表示 Tree 中的项。
 * @author:  yidi
 */
public class TreeItem<T>
{
    private Object parentValue;
    
    private String text;
    
    private Object value;
    
    private T properties;

    private ArrayList<TreeItem<T>> items;
    
    public Object getParentValue()
    {
        return parentValue;
    }

    public void setParentValue(Object parentValue)
    {
        this.parentValue = parentValue;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public T getProperties()
    {
        return properties;
    }

    public void setProperties(T properties)
    {
        this.properties = properties;
    }

    public ArrayList<TreeItem<T>> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<TreeItem<T>> items)
    {
        this.items = items;
    }
}
