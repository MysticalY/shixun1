package com.example.model_home.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.model_home.R;
import com.example.model_home.adapter.FinishAdapter;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;

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
            if (all.get(i).isReceipt()){
                list.add(all.get(i));
            }
        }
        if (adapter==null) {
            adapter = new FinishAdapter(list);
            finishRy.setAdapter(adapter);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {

                }
            });
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (adapter!=null){
            adapter.getData().clear();
            paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
            all = paymentDao.findAll();

            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).isReceipt()){
                    list.add(all.get(i));
                }
            }
            adapter.getData().addAll(list);
            adapter.notifyDataSetChanged();

        }

    }
}
