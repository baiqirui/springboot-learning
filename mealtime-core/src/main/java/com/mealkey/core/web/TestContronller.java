package com.mealkey.core.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealkey.core.exception.ArgumentException;
import com.mealkey.core.response.ResultBody;

@RestController
public class TestContronller
{
    
    @GetMapping("test")
    public ResultBody test()
    {
        throw ArgumentException.createNullOrBlankString("name");
    }
    
}
