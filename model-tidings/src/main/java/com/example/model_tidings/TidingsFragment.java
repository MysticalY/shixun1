package com.example.model_tidings;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterPath;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:15:35
 * author:yanghaoyang
 */
@Route(path = ARouterPath.tidings.PAGER_TIDINGS)
public class TidingsFragment extends BaseFragment {
    @Override
    public int bindLayout() {
        return R.layout.fragment_tidings;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
