package com.example.e_commerce;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:14:23
 * author:yanghaoyang
 */
public class App extends Application {
    public static Context context ;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化需要初始化的组件
        context = this;
        ARouter.openDebug();
        ARouter.openLog();

        ARouter.init(this);

    }
}
