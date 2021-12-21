package com.example.library_base.presenter

import com.example.library_base.model.IModel
import com.example.library_base.view.IView

/**
 *_ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *__╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *_____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *_____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:14:36
 * author:YangHaoYang
 * Package com.example.library_base.presenter
 */
public open class BasePresenter<M : IModel,V : IView> : IPresenter {
    protected lateinit var mPresenter:M;
    protected lateinit var vPresenter:V;

    constructor(mPresenter: M, vPresenter: V) {
        this.mPresenter = mPresenter
        this.vPresenter = vPresenter
    }

    override fun destroy() {

    }
}