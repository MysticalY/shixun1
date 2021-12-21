package com.example.library_base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.library_base.presenter.IPresenter

/**
 *     ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *     ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *      ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *       ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *        ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *        ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:38
 * author:yanghaoyang
 */
public  open abstract class BaseFragment<P : IPresenter>() : Fragment(),IFragment {
    protected lateinit var pFragment:P;
    protected lateinit var baseView: View;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(bindLayout(),container,false).also { baseView = it  };
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();
        initData();
    }

    override fun <T : View?> findViewById(id: Int): T {
        return baseView.findViewById<T>(id);
    }

    override fun showDiaLog() {

    }

    override fun hindDiaLog() {

    }

    override fun showToast() {

    }
}
