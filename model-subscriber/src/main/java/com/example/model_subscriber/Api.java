package com.example.model_subscriber;

import com.example.model_subscriber.bean.SubscriberBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:18:03
 * author:YangHaoYang
 * Package com.example.model_subscriber
 */
public interface Api {
    @POST("api/User/register")
    Observable<SubscriberBean> getRegister(@Body SubscriberBean.DataBean dataBean);
    @POST("api/User/login")
    Observable<SubscriberBean> getLoginBean(@Body SubscriberBean.DataBean dataBean);
}
