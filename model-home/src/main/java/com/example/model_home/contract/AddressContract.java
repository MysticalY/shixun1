package com.example.model_home.contract;

import com.example.library_base.model.IModel;
import com.example.library_base.view.IView;
import com.example.model_home.bean.AddressBean;

import io.reactivex.Observer;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/21
 * Time:11:25
 * author:YangHaoYang
 * Package com.example.model_home.contract
 */
public interface AddressContract {
    interface geiAddressView extends IView{
        void getAddressBean (AddressBean bean);
    }
    interface getAddressModel extends IModel{
        void requestAddress(String goodscode, Observer<AddressBean> observer);
    }
}
