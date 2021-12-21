package com.example.library_community.util;

import android.util.Log;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:13:33
 * author:yanghaoyang
 */
public class LogUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数
    private static String createLog(String log){
        StringBuffer buffer = new StringBuffer();
        buffer.append("===========");
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")=============:");
        buffer.append(log);
        return buffer.toString();
    }
    //获取文件名 方法名 所在行数
    private static void getMethNames(StackTraceElement[] sElements){
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }
    public static  void e(String message){
        getMethNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));

    }
    public static  void i(String message){
        getMethNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));

    }
    public static  void d(String message){
        getMethNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));

    }
    public static  void v(String message){
        getMethNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));

    }
    public static  void w(String message){
        getMethNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

}
