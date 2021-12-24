package com.example.model_subscriber.presenter;

import com.example.library_base.presenter.BasePresenter;
import com.example.model_subscriber.bean.SubscriberBean;
import com.example.model_subscriber.contract.LoginContract;
import com.example.model_subscriber.contract.RegisterContract;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:19:01
 * author:YangHaoYang
 * Package com.example.model_subscriber.presenter
 */
public class LoginPresenter extends BasePresenter<LoginContract.SubscriberModel, LoginContract.SubscriberView> {
    public LoginPresenter(@NotNull LoginContract.SubscriberModel mPresenter, @NotNull LoginContract.SubscriberView vPresenter) {
        super(mPresenter, vPresenter);
    }


    public void getLogin(SubscriberBean.DataBean dataBean){
        mPresenter.RequestLogin(dataBean, new Observer<SubscriberBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull SubscriberBean subscriberBean) {
                vPresenter.getLogin(subscriberBean);
            }

            @Override
            public void onError(@NotNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}


