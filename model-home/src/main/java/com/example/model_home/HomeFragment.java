package com.example.model_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterPath;
import com.example.library_community.shappingdb.ShoppingBean;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;
import com.example.model_home.ui.HomeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:50
 * author:yanghaoyang
 */
@Route(path = ARouterPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment {
    private Button inster;
    private Button query;
    private CircleImageView fragmentImg;
    private LinearLayout homeLayout;
    private PaymentDao paymentDao;
    private PaymentBean paymentBean;
    private TextView homePay;
    private TextView homeReceipt;
    private TextView homeFinish;
    private TextView homeOrder;


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        paymentBean = new PaymentBean();
        fragmentImg = (CircleImageView) findViewById(R.id.fragment_img);
        homeLayout = (LinearLayout) findViewById(R.id.home_layout);
        homePay = (TextView) findViewById(R.id.home_pay);
        homeReceipt = (TextView) findViewById(R.id.home_receipt);
        homeFinish = (TextView) findViewById(R.id.home_finish);
        homeOrder = (TextView) findViewById(R.id.home_order);
    }

    @Override
    public void initData() {
        paymentDao = PaymentDataBean.getInstance(getActivity()).getPaymentDao();

        homeReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getPay(List<ShoppingBean> shoppingBeans) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < shoppingBeans.size(); i++) {

                    paymentBean.setPic(shoppingBeans.get(i).getPicUrl());
                    paymentBean.setTitle(shoppingBeans.get(i).getTitle());
                    paymentBean.setPrice(shoppingBeans.get(i).getMoney());
                    paymentDao.inserts(paymentBean);
                }
            }
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
