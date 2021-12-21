package com.example.model_classify.contract;

import com.example.library_base.model.IModel;
import com.example.library_base.view.IView;
import com.example.model_classify.bean.ClassTextBean;
import com.example.model_classify.bean.GoodsBean;

import io.reactivex.Observer;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:14:07
 * author:YangHaoYang
 * Package com.example.model_classify.view
 */
public interface ClassContract {
    interface  ClassView extends IView{
        void getClassText(ClassTextBean bean);
        void getGoods(GoodsBean bean);
    }
    interface ClassModel extends IModel{
        void requestText(Observer<ClassTextBean> beanObserver);
        void requestGoods(String names,String name,Observer<GoodsBean> observer);
    }

}
