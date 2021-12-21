package com.example.model_home.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.model_home.R;
import com.example.model_home.adapter.HomeAdapter;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;

import org.jetbrains.annotations.NotNull;

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
public class ReceiptFragment extends BaseFragment {
    private RecyclerView receiptRy;
    private PaymentDao paymentDao;
    private HomeAdapter homeAdapter;

    @Override
    public int bindLayout() {
        return R.layout.receiptfragment;
    }

    @Override
    public void initView() {

        receiptRy = (RecyclerView) findViewById(R.id.receipt_ry);
        receiptRy.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {
        paymentDao = PaymentDataBean.getInstance(getActivity()).getPaymentDao();
        List<PaymentBean> all = paymentDao.findAll();
        if (homeAdapter==null){
            homeAdapter = new HomeAdapter(all);
            receiptRy.setAdapter(homeAdapter);
        }
        homeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                if (view.getId()==R.id.mine_Receiving_item_look){
                    for (int i = 0; i < homeAdapter.getData().size(); i++) {
                        if (homeAdapter.getData().get(i).isReceipt()){
                            homeAdapter.getData().get(i).setReceipt(false);
                            paymentDao.find(homeAdapter.getData().get(i).getId());
                            paymentDao.upDatas(homeAdapter.getData().get(i));
                        }else {
                            homeAdapter.getData().get(i).setReceipt(true);
                            paymentDao.find(homeAdapter.getData().get(i).getId());
                            paymentDao.upDatas(homeAdapter.getData().get(i));
                        }

                    }
                }
            }
        });
    }
}
