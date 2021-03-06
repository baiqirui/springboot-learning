package com.mealkey.core.format;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TwoOptionalSerializer extends JsonSerializer<Double>
{
    private static final ThreadLocal<DecimalFormat> numberFormat 
        = new ThreadLocal<DecimalFormat>()
    {
        @Override
        protected DecimalFormat initialValue()
        {
            return new DecimalFormat("0.##");
        }
    };
    
    @Override
    public void serialize(Double value, JsonGenerator jgen, 
        SerializerProvider provider) 
        throws IOException, JsonProcessingException
    {
        String formattedDate = numberFormat.get().format(value);  
        jgen.writeString(formattedDate);  
    }
}

