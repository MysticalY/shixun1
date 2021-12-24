package com.example.model_subscriber.presenter;

import com.example.library_base.presenter.BasePresenter;
import com.example.library_community.util.LogUtils;
import com.example.model_subscriber.bean.SubscriberBean;
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
 * Time:18:25
 * author:YangHaoYang
 * Package com.example.model_subscriber.presenter
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.SubscriberModel, RegisterContract.SubscriberView> {

    public RegisterPresenter(@NotNull RegisterContract.SubscriberModel mPresenter, @NotNull RegisterContract.SubscriberView vPresenter) {
        super(mPresenter, vPresenter);
    }
    public void getRegister(SubscriberBean.DataBean bean){
        mPresenter.RequestRegister(bean, new Observer<SubscriberBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull SubscriberBean subscriberBean) {
                if (subscriberBean.getCode()==200){
                    LogUtils.i("注册成功成功");
                }else {
                    throw new UnsupportedOperationException("注册失败");
                }
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
