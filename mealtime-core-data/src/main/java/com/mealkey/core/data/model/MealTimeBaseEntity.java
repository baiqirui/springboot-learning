package com.mealkey.core.data.model;

import com.mealkey.core.data.model.BaseEntity;

public class MealTimeBaseEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    
    private Long tenantId;

    public Long getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(Long tenantId)
    {
        this.tenantId = tenantId;
    }
}
