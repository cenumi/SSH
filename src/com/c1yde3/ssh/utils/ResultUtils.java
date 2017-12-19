package com.c1yde3.ssh.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wangyonghao8 on 2017/12/19.
 */
public class ResultUtils {
    /**
     * 函数说明，这里是前后端分离使用的接口，废了
     * @param response
     * @param data
     * @throws IOException
     */
    public static void toJson(HttpServletResponse response, Object data)
            throws IOException {
        Gson gson = new Gson();
        String result = gson.toJson(data);
        response.setContentType("text/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
