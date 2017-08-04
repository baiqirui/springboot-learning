package com.mealkey.core.logback.logstash;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * Created by lvjj on 2017/5/23.
 */
public class LogstashJsonFactoryDecorator implements JsonFactoryDecorator
{
    
    @Override
    public MappingJsonFactory decorate(MappingJsonFactory factory)
    {
        ObjectMapper codec = factory.getCodec();
        codec.setDateFormat(new ISO8601DateFormat());
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new StringUnicodeSerializer());
        codec.registerModule(module);
        return factory;
    }
}