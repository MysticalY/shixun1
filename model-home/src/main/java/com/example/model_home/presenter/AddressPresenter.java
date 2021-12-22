package com.example.model_home.presenter;

import com.example.library_base.presenter.BasePresenter;
import com.example.model_home.bean.AddressBean;
import com.example.model_home.contract.AddressContract;

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
 * Date:2021/12/21
 * Time:13:05
 * author:YangHaoYang
 * Package com.example.model_home.presenter
 */
public class AddressPresenter extends BasePresenter<AddressContract.getAddressModel,AddressContract.geiAddressView> {
    public AddressPresenter(@NotNull AddressContract.getAddressModel mPresenter, @NotNull AddressContract.geiAddressView vPresenter) {
        super(mPresenter, vPresenter);
    }
    public void getAddress(String goodscode){
        mPresenter.requestAddress(goodscode, new Observer<AddressBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull AddressBean addressBean) {
                vPresenter.getAddressBean(addressBean);
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
