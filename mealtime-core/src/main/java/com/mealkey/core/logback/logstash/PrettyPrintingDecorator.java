package com.mealkey.core.logback.logstash;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import net.logstash.logback.decorate.JsonGeneratorDecorator;

/**
 * Created by lvjj on 2017/5/23.
 */
public class PrettyPrintingDecorator implements JsonGeneratorDecorator
{
    
    @Override
    public JsonGenerator decorate(JsonGenerator generator)
    {
        generator.setCharacterEscapes(new JsonpCharacterEscapes());
        return generator.useDefaultPrettyPrinter();
    }
    
}
