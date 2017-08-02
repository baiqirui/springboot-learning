package com.mealkey.core.data.config;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


@Component(value = "stringRedisTemplateWapper")
public class StringRedisTemplateWapper
{
    /**
     * 日志记录
     */
    private Logger logger = Logger.getLogger(StringRedisTemplateWapper.class);
    
    
    @Autowired
    protected StringRedisTemplate redisTemplate;
    
    /**
     * 前缀（预留常量）
     */
    public static final String KEY_PREFIX_VALUE = "";
    
    public static final String KEY_PREFIX_SET = "";
    
    public static final String KEY_PREFIX_LIST = "";
    
    /**
     * 缓存value操作
     * 
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheValue(String key, String value, long time)
    {
        try
        {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, value);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }
    
    /**
     * 缓存value操作
     * 
     * @param k
     * @param v
     * @return
     */
    public boolean cacheValue(String k, String v)
    {
        return cacheValue(k, v, -1);
    }
    
    /**
     * 判断缓存是否存在
     * 
     * @param k
     * @return
     */
    public boolean containsValueKey(String k)
    {
        return containsKey(KEY_PREFIX_VALUE + k);
    }
    
    /**
     * 判断缓存是否存在
     * 
     * @param k
     * @return
     */
    public boolean containsSetKey(String k)
    {
        return containsKey(KEY_PREFIX_SET + k);
    }
    
    /**
     * 判断缓存是否存在
     * 
     * @param k
     * @return
     */
    public boolean containsListKey(String k)
    {
        return containsKey(KEY_PREFIX_LIST + k);
    }
    
    public boolean containsKey(String key)
    {
        try
        {
            return redisTemplate.hasKey(key);
        }
        catch (Throwable t)
        {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
    
    /**
     * 获取缓存
     * 
     * @param k
     * @return
     */
    public String getValue(String k)
    {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(KEY_PREFIX_VALUE + k);
    }
    
    /**
     * 移除缓存
     * 
     * @param k
     * @return
     */
    public boolean removeValue(String k)
    {
        return remove(KEY_PREFIX_VALUE + k);
    }
    
    public boolean removeSet(String k)
    {
        return remove(KEY_PREFIX_SET + k);
    }
    
    public boolean removeList(String k)
    {
        return remove(KEY_PREFIX_LIST + k);
    }
    
    /**
     * 移除缓存
     * 
     * @param key
     * @return
     */
    public boolean remove(String key)
    {
        try
        {
            redisTemplate.delete(key);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
    
    /**
     * 缓存set操作
     * 
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, String v, long time)
    {
        String key = KEY_PREFIX_SET + k;
        try
        {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
     * 缓存set
     * 
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, String v)
    {
        return cacheSet(k, v, -1);
    }
    
    /**
     * 缓存set
     * 
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, Set<String> v, long time)
    {
        String key = KEY_PREFIX_SET + k;
        try
        {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
     * 缓存set
     * 
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, Set<String> v)
    {
        return cacheSet(k, v, -1);
    }
    
    /**
     * 获取缓存set数据
     * 
     * @param k
     * @return
     */
    public Set<String> getSet(String k)
    {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(KEY_PREFIX_SET + k);
    }
    
    /**
     * list缓存
     * 
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, String v, long time)
    {
        String key = KEY_PREFIX_LIST + k;
        try
        {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
     * 缓存list
     * 
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, String v)
    {
        return cacheList(k, v, -1);
    }
    
    /**
     * 缓存list
     * 
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, List<String> v, long time)
    {
        String key = KEY_PREFIX_LIST + k;
        try
        {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0)
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
     * 缓存list
     * 
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, List<String> v)
    {
        return cacheList(k, v, -1);
    }
    
    /**
     * 获取list缓存
     * 
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String k, long start, long end)
    {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(KEY_PREFIX_LIST + k, start, end);
    }
    
    /**
     * 获取总条数, 可用于分页
     * 
     * @param k
     * @return
     */
    public long getListSize(String k)
    {
        try
        {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(KEY_PREFIX_LIST + k);
        }
        catch (Throwable t)
        {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }
    
    /**
     * 获取总条数, 可用于分页
     * 
     * @param listOps
     * @param k
     * @return
     */
    public long getListSize(ListOperations<String, String> listOps, String k)
    {
        try
        {
            return listOps.size(KEY_PREFIX_LIST + k);
        }
        catch (Throwable t)
        {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }
    
    /**
     * 移除list缓存
     * 
     * @param k
     * @return
     */
    public boolean removeOneOfList(String k)
    {
        String key = KEY_PREFIX_LIST + k;
        try
        {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(KEY_PREFIX_LIST + key);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("移除list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return false;
    }
}