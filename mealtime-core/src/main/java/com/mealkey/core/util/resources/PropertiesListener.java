package com.mealkey.core.util.resources;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class PropertiesListener implements ApplicationListener<ApplicationStartingEvent>
{
    
    private String propertyFileName;
    
    public PropertiesListener(String propertyFileName)
    {
        this.propertyFileName = propertyFileName;
    }
    
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event)
    {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
    
}
