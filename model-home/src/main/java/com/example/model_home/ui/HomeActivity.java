package com.example.model_home.ui;

import android.app.Application;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
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
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

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
    private Toolbar homeTb;

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        list = new ArrayList<>();
        homeSt = findViewById(R.id.home_st);
        homeVp =  findViewById(R.id.home_vp);
        homeTb =  findViewById(R.id.home_tb);
        setSupportActionBar(homeTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeTb.setNavigationIcon(R.mipmap.icon_back);
        homeTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        int page = getIntent().getIntExtra("page",0);
        list.add(new PayFragment());
        list.add(new ReceiptFragment());
        list.add(new FinishFragment());
        list.add(new OrderFragment());
        homeSt.setViewPager(homeVp,new String[]{"待付款","待收货","已完成","我的订单"},this,list);
        homeSt.setCurrentTab(page);
    }
}
