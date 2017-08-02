package com.mealkey.core.data.config;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;



@Component(value = "objectRedisTemplateWapper")
public class ObjectRedisTemplateWapper
{
    /**
     * 日志记录
     */
    private Logger logger = Logger.getLogger(ObjectRedisTemplateWapper.class);
    
    @Autowired
    protected RedisTemplate<String, Object> objRedisTemplate;
    
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
    public boolean cacheValue(String key, Object value, long time)
    {
        try
        {
            ValueOperations<String, Object> valueOps = objRedisTemplate.opsForValue();
            valueOps.set(key, value);
            if (time > 0)
                objRedisTemplate.expire(key, time, TimeUnit.SECONDS);
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
    public boolean cacheValue(String k, Object v)
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
            return objRedisTemplate.hasKey(key);
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
    public Object getValue(String k)
    {
        ValueOperations<String, Object> valueOps = objRedisTemplate.opsForValue();
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
            objRedisTemplate.delete(key);
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
    public boolean cacheSet(String k, Object v, long time)
    {
        String key = KEY_PREFIX_SET + k;
        try
        {
            SetOperations<String, Object> valueOps = objRedisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0)
                objRedisTemplate.expire(key, time, TimeUnit.SECONDS);
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
    public boolean cacheSet(String k, Object v)
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
    public boolean cacheSet(String k, Set<Object> v, long time)
    {
        String key = KEY_PREFIX_SET + k;
        try
        {
            SetOperations<String, Object> setOps = objRedisTemplate.opsForSet();
            setOps.add(key, v.toArray(new Object[v.size()]));
            if (time > 0)
                objRedisTemplate.expire(key, time, TimeUnit.SECONDS);
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
    public boolean cacheSet(String k, Set<Object> v)
    {
        return cacheSet(k, v, -1);
    }
    
    /**
     * 获取缓存set数据
     * 
     * @param k
     * @return
     */
    public Set<Object> getSet(String k)
    {
            SetOperations<String, Object> setOps = objRedisTemplate.opsForSet();
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
    public boolean cacheList(String k, Object v, long time)
    {
        String key = KEY_PREFIX_LIST + k;
        try
        {
            ListOperations<String, Object> listOps = objRedisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0)
                objRedisTemplate.expire(key, time, TimeUnit.SECONDS);
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
    public boolean cacheList(String k, List<Object> v, long time)
    {
        String key = KEY_PREFIX_LIST + k;
        try
        {
            ListOperations<String, Object> listOps = objRedisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0)
                objRedisTemplate.expire(key, time, TimeUnit.SECONDS);
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
    public boolean cacheList(String k, List<Object> v)
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
    public List<Object> getList(String k, long start, long end)
    {
            ListOperations<String, Object> listOps = objRedisTemplate.opsForList();
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
            ListOperations<String, Object> listOps = objRedisTemplate.opsForList();
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
            ListOperations<String, Object> listOps = objRedisTemplate.opsForList();
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