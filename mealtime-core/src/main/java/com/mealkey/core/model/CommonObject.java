package com.mealkey.core.model;

public class CommonObject
{
    private Long tenantId;
    
    private Long storeId;
    
    private Long userId;
    
    private String sessionId;
    
    public Long getTenantId()
    {
        return tenantId;
    }
    
    public void setTenantId(Long tenantId)
    {
        this.tenantId = tenantId;
    }
    
    public Long getStoreId()
    {
        return storeId;
    }
    
    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }
    
    public Long getUserId()
    {
        return userId;
    }
    
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    
    public String getSessionId()
    {
        return sessionId;
    }
    
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
    
    @Override
    public String toString()
    {
        return "CommonObject [tenantId=" + tenantId + ", storeId=" + storeId + ", userId=" + userId + ", sessionId="
            + sessionId + "]";
    }
}