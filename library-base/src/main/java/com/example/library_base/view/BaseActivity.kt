package com.example.library_base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.library_base.presenter.IPresenter

/**
 *     ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *     ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *      ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *       ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *        ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *        ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:35
 * author:yanghaoyang
 */
public abstract  class BaseActivity<P : IPresenter> : AppCompatActivity(),IActivity {
    protected lateinit var pActivity:P;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout());
        initView();
        initData();
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (pActivity!=null){
//            pActivity.destroy();
//            pActivity == null;
//        }
    }

    override fun showDiaLog() {
    }

    override fun hindDiaLog() {

    }

    override fun showToast() {
    }


}