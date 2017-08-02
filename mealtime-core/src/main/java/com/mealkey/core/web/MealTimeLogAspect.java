package com.mealkey.core.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.mealkey.core.format.JsonObjectMapper;

@Aspect
@Component
public class MealTimeLogAspect
{
    private Logger logger = Logger.getLogger(MealTimeLogAspect.class);
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    
    private ThreadLocal<String> actionPath = new ThreadLocal<String>();
    
    private ThreadLocal<String> threadId = new ThreadLocal<String>();
    
    private JsonObjectMapper mapper = new JsonObjectMapper();
    
    @Resource
    private HttpServletRequest request;
    
    @Pointcut("execution(public * com.mealkey.mealtime..action..**.**(..))")
    public void logAspect()
    {
    }
    
    @Before("logAspect()")
    public void doBefore(JoinPoint joinPoint)
        throws Throwable
    {
        startTime.set(System.currentTimeMillis());
        threadId.set(String.valueOf(Thread.currentThread().getId()));
        StringBuilder sb = new StringBuilder("\n开始执行接口 ------------------- ").append(dateFormat.format(new Date())).append("   线程ID：").append(threadId.get())
            .append(" ------------------------------\n");
        
        sb.append("Controller  : ")
            .append(joinPoint.getTarget().getClass().getName())
            .append("\nMethod      : ")
            .append(joinPoint.getSignature().getName())
            .append("\n");
        
        String uri = request.getRequestURI();
        actionPath.set(uri);
        if (uri != null)
        {
            sb.append("url         : ").append(uri).append("\n");
        }
        Enumeration<String> e = request.getParameterNames();
        sb.append("Parameter   : ");
        if (e.hasMoreElements())
        {
            while (e.hasMoreElements())
            {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1)
                {
                    sb.append(name).append("=").append(values[0]);
                }
                else
                {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++)
                    {
                        if (i > 0)
                            sb.append(",");
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
        }
        else
        {
            Object obj = null;
            try
            {
                for (int i = 0; i < joinPoint.getArgs().length; i++)
                {
                    obj = joinPoint.getArgs()[i];
                    sb.append(mapper.writeValueAsString(obj));
                }
            }
            catch (Exception e2)
            {
                logger.error("无法转换成JSON的参数：" + obj);
            }
         
        }
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------\n");
        logger.info(sb.toString());
    }
    
    @AfterReturning(returning = "ret", pointcut = "logAspect()")
    public void doAfterReturning(Object ret)
        throws Throwable
    {
        // 处理完请求
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------\n");
        sb.append("结束执行接口 : ")
            .append(actionPath.get())
            .append("   线程ID： ").append(threadId.get())
            .append("     共消耗时间： ")
            .append(System.currentTimeMillis() - startTime.get())
            .append(" ms")
            .append("\n")
            .append("返回值： ")
            .append(mapper.writeValueAsString(ret))
            .append("\n");
        sb.append("--------------------------------------------------------------------------------\n");
        logger.info(sb.toString());
    }
}