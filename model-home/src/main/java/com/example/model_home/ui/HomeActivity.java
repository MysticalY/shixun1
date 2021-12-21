package com.example.model_home.ui;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.library_base.view.BaseActivity;
import com.example.model_home.R;
import com.example.model_home.adapter.HomeAdapter;
import com.example.model_home.fragment.FinishFragment;
import com.example.model_home.fragment.OrderFragment;
import com.example.model_home.fragment.PayFragment;
import com.example.model_home.fragment.ReceiptFragment;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/20
 * Time:10:15
 * author:YangHaoYang
 * Package com.example.model_home.ui
 */
public class HomeActivity extends BaseActivity {
    private RecyclerView homeRy;
    private com.flyco.tablayout.SlidingTabLayout homeSt;
    private androidx.viewpager.widget.ViewPager homeVp;
    private ArrayList<Fragment> list;

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        homeSt = findViewById(R.id.home_st);
        homeVp =  findViewById(R.id.home_vp);
    }

    @Override
    public void initData() {
        list.add(new PayFragment());
        list.add(new ReceiptFragment());
        list.add(new FinishFragment());
        list.add(new OrderFragment());
        homeSt.setViewPager(homeVp,new String[]{"待付款","待收货","已完成","我的订单"},this,list);
        homeSt.setCurrentTab(2);
    }
}
