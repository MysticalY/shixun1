package com.example.model_home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

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
import com.example.model_home.ui.DeliveryActivity;

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
public class ReceiptFragment extends BaseFragment {
    private RecyclerView receiptRy;
    private PaymentDao paymentDao;
    private HomeAdapter homeAdapter;
    private List<PaymentBean> list;
    private List<PaymentBean> all;
    private AlertDialog.Builder builder;
    private List<PaymentBean> paymentBeans;

    @Override
    public int bindLayout() {
        return R.layout.receiptfragment;
    }

    @Override
    public void initView() {

        receiptRy = (RecyclerView) findViewById(R.id.receipt_ry);
        receiptRy.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        builder = new AlertDialog.Builder(getContext());
        paymentBeans = new ArrayList<>();
        paymentDao = PaymentDataBean.getInstance(getActivity()).getPaymentDao();
    }

    @Override
    public void initData() {
        all = paymentDao.findAll();
        if (all!=null){
            for (int i = 0; i < all.size(); i++) {
                if (!all.get(i).isReceipt()){
                    list.add(all.get(i));
                }
                if (homeAdapter==null){
                    homeAdapter = new HomeAdapter(list);
                    receiptRy.setAdapter(homeAdapter);
                    homeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                            if (view.getId()==R.id.mine_Receiving_item_affirm){

                                builder.setMessage("是否要收货");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int s) {
                                        if (!homeAdapter.getData().get(position).isReceipt()){
                                            Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                                            PaymentBean paymentBean = paymentDao.find(homeAdapter.getData().get(position).getId());
                                            paymentBean.setReceipt(true);
                                            paymentDao.upDatas(paymentBean);
                                        }
                                        List<PaymentBean> all = paymentDao.findAll();
                                        for (int i = 0; i < all.size(); i++) {
                                            if (!all.get(i).isReceipt()){
                                                paymentBeans.add(ReceiptFragment.this.all.get(i));
                                            }
                                        }
                                        homeAdapter.getData().clear();
                                        if (list.size() <= 0){
                                            homeAdapter.getData().addAll(paymentBeans);
                                        }else {
                                            homeAdapter.getData().addAll(list);
                                        }

                                        homeAdapter.notifyDataSetChanged();

                                        paymentBeans.clear();
                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder.create().show();

                            }
                            if (view.getId()==R.id.mine_Receiving_item_look){
                                Intent intent = new Intent(getActivity(), DeliveryActivity.class);
                                PaymentBean bean = homeAdapter.getData().get(position);
                                intent.putExtra("paymentbean",bean);
                                startActivity(intent);
                            }
                        }
                    });
                }
        }
        }else {
            Toast.makeText(getContext(), "购物车没有信息", Toast.LENGTH_SHORT).show();
        }


    }
}
