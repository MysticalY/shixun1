package com.example.model_principal.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.example.library_base.presenter.BasePresenter;
import com.example.model_principal.bean.GroomBean;
import com.example.model_principal.contract.GroomContract;

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
 * Date:2021/12/13
 * Time:14:42
 * author:YangHaoYang
 * Package com.example.model_principal.presenter
 */
public class GroomPresenter extends BasePresenter<GroomContract.GroomModel,GroomContract.GroomView> {

    public GroomPresenter(@NotNull GroomContract.GroomModel mPresenter, @NotNull GroomContract.GroomView vPresenter) {
        super(mPresenter, vPresenter);
    }
    public void getGroom(int page,int pageSize){
        mPresenter.request(page, pageSize, new Observer<GroomBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull GroomBean bean) {
                vPresenter.getGroomBean(bean);
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
