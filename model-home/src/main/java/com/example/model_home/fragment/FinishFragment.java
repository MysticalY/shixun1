package com.example.model_home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.util.LogUtils;
import com.example.model_home.R;
import com.example.model_home.adapter.FinishAdapter;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;
import com.example.model_home.ui.AppraiseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

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
 * Time:13:58
 * author:YangHaoYang
 * Package com.example.model_home.fragment
 */
public class FinishFragment extends BaseFragment {

    private RecyclerView finishRy;
    private FinishAdapter adapter;
    private PaymentDao paymentDao;
    private List<PaymentBean> list;
    private List<PaymentBean> all;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.finishfragment;
    }

    @Override
    public void initView() {

        finishRy = (RecyclerView) findViewById(R.id.finish_ry);
        paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
        all = paymentDao.findAll();
        finishRy.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        list = new ArrayList<>();
    }

    @Override
    public void initData() {
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isReceipt()&&!all.get(i).isEvaluation()){
                list.add(all.get(i));
                LogUtils.i("1"+list.size());
            }
        }
        if (adapter==null) {
            adapter = new FinishAdapter(list);
            finishRy.setAdapter(adapter);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                    if (view.getId()==R.id.home_Receiving_item_appraise){
                        PaymentBean paymentBean  = list.get(position);
                        Intent intent  = new Intent(getActivity(), AppraiseActivity.class);
                        intent.putExtra("appraise",paymentBean);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (adapter!=null){
            List<PaymentBean> list1 = new ArrayList<>();
            adapter.getData().clear();
            paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
            all = paymentDao.findAll();

            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).isReceipt()&&!all.get(i).isEvaluation()){
                    list1.add(all.get(i));

                }
            }
            adapter.getData().addAll(list1);
            adapter.notifyDataSetChanged();

        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getNotify(String msg){
        if (msg.equals("appraise")){
            List<PaymentBean> paymentBeans = new ArrayList<>();
            adapter.getData().clear();
            paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
            all = paymentDao.findAll();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).isReceipt()&&!all.get(i).isEvaluation()){
                    paymentBeans.add(all.get(i));
                }
            }

            adapter.getData().addAll(paymentBeans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
