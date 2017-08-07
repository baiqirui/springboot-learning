package com.mealkey.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mealkey.core.util.resources.PropertiesListener;

@SpringBootApplication
public class CoreApplication
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(CoreApplication.class);
        application.addListeners(new PropertiesListener("result_code.properties"));
        application.run(args);
        
    }
}
