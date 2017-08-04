package com.mealkey.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.mealkey.core.response.ResultBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常拦截器;
 * 
 * @author baiqirui
 * @version [版本号, 2017年8月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver
{
    
    @ExceptionHandler({Exception.class})
    public ResultBody processException(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        log.error("公共异常处理：", e);
        
        // 判断是否是系统内部自定义异常;
        if (e instanceof ExceptionBase)
        {
            ExceptionBase exb = (ExceptionBase)e;
            return new ResultBody(exb.getErrorCode(), exb.getMessage());
        }
        else
        {
            return ResultBody.createUnKnowExceptionResultBody();
        }
    }
}
