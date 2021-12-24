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
import com.example.library_community.util.SpUtil;
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
public class HomeFragment extends BaseFragment implements View.OnClickListener {
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
    private TextView homeName;


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
        homeName = (TextView) findViewById(R.id.home_name);
    }

    @Override
    public void initData() {
        String name = (String) SpUtil.getInstance().get(getContext(), "name", "", "name.xml");
        homeName.setText(name);
        paymentDao = PaymentDataBean.getInstance(getActivity()).getPaymentDao();
        homePay.setOnClickListener(this);
        homeReceipt.setOnClickListener(this);
        homeFinish.setOnClickListener(this);
        homeOrder.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.home_pay) {

            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra("page", 0);
            startActivity(intent);
        }
        if (view.getId() == R.id.home_receipt) {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra("page", 1);
            startActivity(intent);
        }
        if (view.getId() == R.id.home_finish) {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra("page", 2);
            startActivity(intent);
        }
        if (view.getId() == R.id.home_order) {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra("page", 3);
            startActivity(intent);
        }
    }
}
