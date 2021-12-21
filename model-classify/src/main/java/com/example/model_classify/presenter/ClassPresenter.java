package com.example.model_classify.presenter;

import com.example.library_base.presenter.BasePresenter;
import com.example.model_classify.bean.ClassTextBean;
import com.example.model_classify.bean.GoodsBean;
import com.example.model_classify.contract.ClassContract;

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
 * Date:2021/12/14
 * Time:8:25
 * author:YangHaoYang
 * Package com.example.model_classify.model
 */
public class ClassPresenter extends BasePresenter<ClassContract.ClassModel,ClassContract.ClassView> {

    public ClassPresenter(@NotNull ClassContract.ClassModel mPresenter, @NotNull ClassContract.ClassView vPresenter) {
        super(mPresenter, vPresenter);
    }

    public void getGoodsBean(String names,String name){
        mPresenter.requestGoods(names,name, new Observer<GoodsBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull GoodsBean bean) {
                vPresenter.getGoods(bean);
            }

            @Override
            public void onError(@NotNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getClassText(){
        mPresenter.requestText(new Observer<ClassTextBean>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull ClassTextBean bean) {
                    vPresenter.getClassText(bean);
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
