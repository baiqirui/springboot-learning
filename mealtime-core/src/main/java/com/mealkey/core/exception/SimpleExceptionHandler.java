package com.mealkey.core.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealkey.core.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.mealkey.core.BooleanReference;
import com.mealkey.core.response.ErrorResponse;
import com.mealkey.core.util.IntegerUtil;
import com.mealkey.core.web.ResponseUtil;

/**
 * 一般异常拦截封装。
 *
 * @author: yidi
 */
@RestControllerAdvice
public class SimpleExceptionHandler extends ExceptionHandlerExceptionResolver {
    private static final int STATUS_UNAUTHORIZED = 401;
    private static final int STATUS_SERVERERROR = 500;

    private static Logger logger = LoggerFactory.getLogger(SimpleExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public Object processException(HttpServletRequest request, 
        HttpServletResponse response, Exception e) {
        ResponseUtil.setCrossDomain(response);

        if ((e instanceof HttpRequestMethodNotSupportedException)
            && HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return new ErrorResponse();
        }

        // 客户端错误
        Integer code = 0;
        String message;
        int status = HttpStatus.BAD_REQUEST.value();
        if (e instanceof IOException)  //IO 错误
        {
            message = "IO 错误。";
            logger.error("IOException:" + request.getRequestURI());
        } else if (e instanceof HttpMediaTypeException) {
            message = "转换错误。";
            logger.error("HttpMediaTypeException:" + request.getRequestURI());
        } else if (e instanceof FieldException) {
            Map<String, Object> stringObjectMap = JsonUtil.jsonToMap(e.getMessage());
            Map result = new HashMap();
            response.setStatus(STATUS_SERVERERROR);
            result.put("code", 0);
            result.put("error", stringObjectMap);
            return result;
        }
        else if ((e instanceof BusinessException) 
            || (e instanceof ServletRequestBindingException)
            || (e instanceof HttpRequestMethodNotSupportedException))
        {
            if (e instanceof UnauthorizedException)
            {
                status = STATUS_UNAUTHORIZED;
            }
            
            message = e.getMessage();
            int start = message.indexOf('[');
            if (start != -1)
            {
                int end = message.indexOf(']',start + 1);
                if (end != -1)
                {
                    code = IntegerUtil.parse(message.substring(start + 1,end),new BooleanReference());
                    if (0 != code) {
                        message = message.substring(end + 1);
                    }
                }
            }
            
            logger.error("Other Exception:" + request.getRequestURI());
            logger.debug(message,e);
        }

        //服务器端错误
        else {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();

            message = "系统好像出错了！等下再试试吧~";
            logger.error("Server Exception:" + request.getRequestURI());
            logger.error(null,e);
        }

        response.setStatus(status);
        return new ErrorResponse(code, message);
    }

    /**
     * MissingServletRequestParameterException
     * @auth lvjj
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ErrorResponse processMissingServletRequestParameterException(HttpServletRequest request,
                                   HttpServletResponse response, MissingServletRequestParameterException e) {
        e.printStackTrace();

        response.setStatus(HttpStatus.BAD_REQUEST.value());

        String msg = "必须输入的 "+e.getParameterType()+" 类型参数 "+ e.getParameterName() +" 为空" ;

        logger.debug(null,e);
        return new ErrorResponse(0, msg);
    }
}
