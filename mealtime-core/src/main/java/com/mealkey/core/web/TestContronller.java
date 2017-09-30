package com.mealkey.core.web;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mealkey.core.exception.ArgumentException;
import com.mealkey.core.response.ResultBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(tags = "测试接口")
public class TestContronller
{
    
    @GetMapping("test1")
    @ApiOperation(value = "测试1", response = ResultBody.class)
    public ResultBody test1(@Validated @NotNull @RequestParam String name)
    {
        throw ArgumentException.createNullOrBlankString("name");
    }
    
    @PostMapping("test2")
    @ApiOperation(value = "测试2", response = ResultBody.class)
    public ResultBody test1(@Validated  @RequestBody  @ApiParam User user)
    {
        throw ArgumentException.createNullOrBlankString("name");
    }
    
    
}
