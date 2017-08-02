package com.mealkey.core.model.transform;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: SelectObject
 * @Description: key-value 对象
 * @author CC.Peng
 * @date 2016年11月18日 下午3:45:00
 * 
 */
@ApiModel(description = "下拉菜单对象")
public class SelectItem
{
    @ApiModelProperty(value = "ID")
    private Long key;

    @ApiModelProperty(value = "值")
    private String value;

    public SelectItem()
    {

    }

    public SelectItem(Long key, String value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    public Long getKey()
    {
        return key;
    }

    public void setKey(Long key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
