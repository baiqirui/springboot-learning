package com.mealkey.core.web;

import javax.servlet.http.HttpServletResponse;

public final class ResponseUtil {
    /**
     * 设置跨域。
     * @param response
     */
    public static void setCrossDomain(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "2592000");
        response.setHeader("Access-Control-Allow-Headers",
            "Content-Type,sessionId,appKey,timestamp,v,sign,TENANT_ID,USER_ID");
    }
}
