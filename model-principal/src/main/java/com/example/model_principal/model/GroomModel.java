package com.example.model_principal.model;

import com.example.library_base.http.HttpRetrofit;
import com.example.library_base.model.BaseModel;
import com.example.model_principal.Api;
import com.example.model_principal.bean.GroomBean;
import com.example.model_principal.contract.GroomContract;

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
 * Date:2021/12/13
 * Time:14:39
 * author:YangHaoYang
 * Package com.example.model_principal.model
 */
public class GroomModel implements GroomContract.GroomModel{
    @Override
    public void request(int page, int pageSize, Observer<GroomBean> observer) {
        HttpRetrofit.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getGroom(page,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void destroy() {

    }
}
