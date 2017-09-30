package com.mealkey.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(CoreApplication.class);
        application.run(args);
    }
}
