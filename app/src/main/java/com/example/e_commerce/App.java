package com.example.e_commerce;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.rtmp.TXLiveBase;

import io.rong.imkit.RongIM;

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
        String licenceURL = "https://license.vod2.myqcloud.com/license/v1/82a565ad2309f572d7a2c5a9c8350652/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey = "2ef843d67dc1d0162f030a6246ba4093"; // 获取到的 licence key
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
        String appKey = "vnroth0kvoako";
        RongIM.init(this, appKey);
    }
}
