package com.example.model_subscriber.model;

import com.example.library_base.http.HttpRetrofit;
import com.example.model_subscriber.Api;
import com.example.model_subscriber.bean.SubscriberBean;
import com.example.model_subscriber.contract.RegisterContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:18:28
 * author:YangHaoYang
 * Package com.example.model_subscriber.model
 */
public class RegisterModel implements RegisterContract.SubscriberModel {
    @Override
    public void RequestRegister(SubscriberBean.DataBean bean, Observer<SubscriberBean> beanObserver) {
        HttpRetrofit.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getRegister(bean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beanObserver);
    }


    @Override
    public void destroy() {

    }
}
