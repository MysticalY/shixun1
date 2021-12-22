package com.example.model_home.model;

import com.example.library_base.http.HttpRetrofit;
import com.example.model_home.Api;
import com.example.model_home.bean.AddressBean;
import com.example.model_home.contract.AddressContract;

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
 * Date:2021/12/21
 * Time:11:28
 * author:YangHaoYang
 * Package com.example.model_home.model
 */
public class AddressModel  implements AddressContract.getAddressModel {
    @Override
    public void requestAddress(String goodscode, Observer<AddressBean> observer) {
        HttpRetrofit.getInstance().getRetrofit("http://82.156.178.182:8082/")
                .create(Api.class)
                .getAddress(goodscode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void destroy() {

    }
}
