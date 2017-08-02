package com.mealkey.core.model.transform;


import com.mealkey.core.exception.ArgumentNullException;
import com.mealkey.core.model.reflection.probe.ObjectProbe;
import com.mealkey.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 提供 Tree 数据格式转换功能。
 * @author: yidi
 */
public class TreeTransform<S> implements Transform<S,TreeItem<S>>
{   
    private String textProperty;
    
    private String valueProperty;
    
    private String parentValueProperty;
    
    public TreeTransform(String textProperty, String valueProperty, 
        String parentValueProperty)
    {
        this.textProperty = textProperty;
        this.valueProperty = valueProperty;
        this.parentValueProperty = parentValueProperty;
    }
    
    public String getTextProperty()
    {
        return textProperty;
    }

    public void setTextProperty(String textProperty)
    {
        ArgumentNullException.checkNull("textProperty",textProperty);
        this.textProperty = textProperty;
    }
    
    public String getParentValueProperty()
    {
        return parentValueProperty;
    }

    public void setParentValueProperty(String parentValueProperty)
    {
        ArgumentNullException.checkNull("parentValueProperty",
            parentValueProperty);
        this.parentValueProperty = parentValueProperty;
    }

    public String getValueProperty()
    {
        return valueProperty;
    }

    public void setValueProperty(String valueProperty)
    {
        ArgumentNullException.checkNull("valueProperty",valueProperty);
        this.valueProperty = valueProperty;
    }

    public List<TreeItem<S>> transform(List<S> list)
    {
        ArrayList<TreeItem<S>> result = new ArrayList<TreeItem<S>>();
        TreeMap<Object,TreeItem<S>> map = new TreeMap<Object,TreeItem<S>>();
        List<Object> idList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            S obj = list.get(i);
            ObjectProbe objectProbe = ObjectUtil.getProbe(obj);
            TreeItem<S> treeItem = new TreeItem<S>();
            treeItem.setText((String)objectProbe.get(textProperty));
            treeItem.setParentValue(objectProbe.get(parentValueProperty));
            treeItem.setValue(objectProbe.get(valueProperty));
            treeItem.setProperties(obj);
            treeItem.setItems(new ArrayList<TreeItem<S>>());
            map.put(treeItem.getValue(),treeItem);
            idList.add(treeItem.getValue());
        }
        
        for (Object id : idList)
        {
            TreeItem<S> treeItem = map.get(id);
            TreeItem<S> parentItem = null;
            if (null != treeItem.getParentValue())
            {
                parentItem = map.get(treeItem.getParentValue());
            }
            if (null == parentItem)
            {
                result.add(treeItem);
            }
            else
            {
                parentItem.getItems().add(treeItem);
            }
        }
        return result;
    }
    
    public List<TreeItem<S>> transformForOrder(List<S> list)
    {
        ArrayList<TreeItem<S>> result = new ArrayList<TreeItem<S>>();
        TreeMap<Object,TreeItem<S>> map = new TreeMap<Object,TreeItem<S>>();
        List<Object> idList = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++)
        {
            S obj = list.get(i);
            ObjectProbe objectProbe = ObjectUtil.getProbe(obj);
            TreeItem<S> treeItem = new TreeItem<S>();
            treeItem.setText((String)objectProbe.get(textProperty));
            treeItem.setParentValue(objectProbe.get(parentValueProperty));
            treeItem.setValue(objectProbe.get(valueProperty));
            treeItem.setProperties(obj);
            treeItem.setItems(new ArrayList<TreeItem<S>>());
            map.put(treeItem.getValue(),treeItem);
            idList.add(treeItem.getValue());
        }
        
        for (Object id : idList)
        {
            TreeItem<S> treeItem = map.get(id);
            TreeItem<S> parentItem = null;
            if (null != treeItem.getParentValue())
            {
                parentItem = map.get(treeItem.getParentValue());
            }
            if (null == parentItem)
            {
                result.add(treeItem);
            }
            else
            {
                parentItem.getItems().add(treeItem);
            }
        }
        return result;
    }
}
