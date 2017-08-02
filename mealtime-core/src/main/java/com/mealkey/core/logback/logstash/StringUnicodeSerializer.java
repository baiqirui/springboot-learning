package com.mealkey.core.logback.logstash;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by lvjj on 2017/5/23.
 */
public class StringUnicodeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String str, JsonGenerator gen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {
        gen.writeUTF8String(str.getBytes(Charset.defaultCharset()),0,str.length());
    }

}