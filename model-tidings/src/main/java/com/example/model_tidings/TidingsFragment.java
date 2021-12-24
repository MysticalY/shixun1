package com.example.model_tidings;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterPath;

import io.rong.imkit.RongIM;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.RongIMClient;

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
    private Button tidingsBut;

    @Override
    public int bindLayout() {
        return R.layout.fragment_tidings;
    }


    @Override
    public void initView() {

        tidingsBut = (Button) findViewById(R.id.tidings_but);
    }

    @Override
    public void initData() {
        tidingsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = "k1Q5E43t/fQGW6AKVwydsLLlwZA4+txyRAdAsHENTiA=@gwdp.cn.rongnav.com;gwdp.cn.rongcfg.com";
                RongIM.connect(token, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onSuccess(String userId) {
                        // 登录成功，跳转到默认会话列表页。
                    }

                    @Override
                    public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {

                    }

                    @Override
                    public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {

                    }
                });
                RouteUtils.routeToConversationListActivity(getContext(), token);
            }
        });
    }
}
