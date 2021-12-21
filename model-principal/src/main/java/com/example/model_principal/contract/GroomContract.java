package com.example.model_principal.contract;

import com.example.library_base.model.IModel;
import com.example.library_base.view.IView;
import com.example.model_principal.bean.GroomBean;

import io.reactivex.Observer;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:14:34
 * author:YangHaoYang
 * Package com.example.model_principal.contract
 */
public interface GroomContract {
    interface GroomView extends IView{
        void getGroomBean(GroomBean bean);

    }
    interface GroomModel extends IModel{
        void request(int page, int pageSize, Observer<GroomBean> observer);
    }

}
