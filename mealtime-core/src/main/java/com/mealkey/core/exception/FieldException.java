package com.mealkey.core.exception;

import com.mealkey.core.util.JsonUtil;
import com.mealkey.core.util.resources.ResourceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段验证错误提示。
 *
 * @author lvjj
 */
public class FieldException extends ExceptionBase {

    public FieldException(String field, String msg) {
        super("{ " +
                "\"field\":\"" + field + "\"," +
                "\"msg\":\"" + msg + "\"" +
                "}");

    }

    public FieldException(String field, String msg, Throwable cause) {
        super("{ " +
                "\"field\":\"" + field + "\"," +
                "\"msg\":\"" + msg + "\"" +
                "}", cause);
    }
}
