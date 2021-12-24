package com.example.model_subscriber.contract;

import com.example.library_base.model.IModel;
import com.example.library_base.view.IView;
import com.example.model_subscriber.bean.SubscriberBean;

import io.reactivex.Observer;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:18:08
 * author:YangHaoYang
 * Package com.example.model_subscriber.contract
 */
public interface RegisterContract {
    interface SubscriberView extends IView{
        void getRegister(SubscriberBean bean);

    }
    interface SubscriberModel extends IModel{
        void RequestRegister(SubscriberBean.DataBean bean, Observer<SubscriberBean> beanObserver);

    }

}
