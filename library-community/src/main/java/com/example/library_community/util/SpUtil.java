package com.example.library_community.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *  ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *  ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *   ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *    ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *     ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *     ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:13:59
 * author:yanghaoyang
 */
public class SpUtil {
    private static  SpUtil spUtil = null;
    private static SharedPreferences sp  = null;
    public static SpUtil getInstance() {
        if (spUtil==null){
            synchronized (SpUtil.class){
                if (spUtil==null){
                    spUtil = new SpUtil();
                }
            }
        }
        return spUtil;
    }

    //将一个boolean值存入sp文件中
    public static void put(Context context,String ket,Object value,String name){
        String type = value.getClass().getSimpleName();
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if ("Integer".equals(type)){
            edit.putInt(ket, (Integer) value);
        }else if ("Boolean".equals(type)){
            edit.putBoolean(ket, (Boolean) value);
        }else if ("Float".equals(type)){
            edit.putFloat(ket, (Float) value);
        }else if ("Long".equals(type)){
            edit.putLong(ket, (Long) value);
        }else if ("String".equals(type)){
            edit.putString(ket, (String) value);
        }
        edit.apply();

    }
    public Object get(Context context,String key, Object defVale,String name){
        sp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        String type = defVale.getClass().getSimpleName();
        if("Integer".equals(type)){
            return sp.getInt(key,(Integer)defVale);
        }else if ("Boolean".equals(type)){
            return sp.getBoolean(key,(Boolean)defVale);
        }else if ("Float".equals(type)){
            return sp.getFloat(key,(Float)defVale);
        }else if ("Long".equals(type)){
            return sp.getLong(key,(Long)defVale);
        }else if ("String".equals(type)){
            return sp.getString(key,(String) defVale);
        }
        return null;
    }
}
