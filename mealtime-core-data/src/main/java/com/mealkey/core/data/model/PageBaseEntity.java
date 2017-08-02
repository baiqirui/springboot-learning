package com.mealkey.core.data.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提供查询条件和设置的公共功能。
 * @author yd
 */
public abstract class PageBaseEntity extends MealTimeBaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="页索引号（从0开始）")
    private Integer page;
    
    @ApiModelProperty(value="页大小")
    private Integer pageSize;
    
    @ApiModelProperty(value="视图格式")
    private String view;
    
    @ApiModelProperty(value="总的行数",hidden=true)
    private Integer total;
    
    @ApiModelProperty(value="数据版本号",hidden=true)
    private Long dbVersion;
    
    @ApiModelProperty(value="排序SQL",hidden=true)
    private String sortSql;
    
    @ApiModelProperty(value="字段限制SQL",hidden=true)
    private String fieldSql;

    @ApiModelProperty(value="数据过滤SQL",hidden=true)
    private String limitSql;

    public String getLimitSql()
    {
        return limitSql;
    }

    public void setLimitSql(String limitSql)
    {
        this.limitSql = limitSql;
    }
    
    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public String getView()
    {
        return view;
    }

    public void setView(String view)
    {
        this.view = view;
    }

    public Long getDbVersion()
    {
        return dbVersion;
    }

    public void setDbVersion(Long dbVersion)
    {
        this.dbVersion = dbVersion;
    }

    public String getSortSql()
    {
        return sortSql;
    }

    public void setSortSql(String sortSql)
    {
        this.sortSql = sortSql;
    }

    public String getFieldSql()
    {
        return fieldSql;
    }

    public void setFieldSql(String fieldSql)
    {
        this.fieldSql = fieldSql;
    }
}
