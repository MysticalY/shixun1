package com.example.model_classify.model;

import com.example.library_base.http.HttpRetrofit;
import com.example.model_classify.Api;
import com.example.model_classify.bean.ClassTextBean;
import com.example.model_classify.bean.GoodsBean;
import com.example.model_classify.contract.ClassContract;

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
 * Date:2021/12/14
 * Time:8:27
 * author:YangHaoYang
 * Package com.example.model_classify.model
 */
public class ClassModel implements ClassContract.ClassModel {
    @Override
    public void requestText(Observer<ClassTextBean> beanObserver) {
        HttpRetrofit.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getClassText()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beanObserver);
    }


    @Override
    public void requestGoods(String names,String name, Observer<GoodsBean> observer) {
        HttpRetrofit.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getGoods(names,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void destroy() {

    }
}
