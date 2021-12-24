package com.example.model_home.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library_base.view.BaseFragment;
import com.example.library_community.util.LogUtils;
import com.example.model_home.R;
import com.example.model_home.adapter.OrderAdapter;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;

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
public class OrderFragment extends BaseFragment {
    private RecyclerView orderRy;
    private OrderAdapter orderAdapter;
    private    PaymentDao paymentDao;
    private List<PaymentBean> list;
    private List<PaymentBean> all;

    @Override
    public int bindLayout() {
        return R.layout.orderfragment;
    }

    @Override
    public void initView() {
        paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
        orderRy = (RecyclerView) findViewById(R.id.order_ry);
        all = paymentDao.findAll();
        list = new ArrayList<>();
        orderRy.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
    }

    @Override
    public void initData() {

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isReceipt()&&all.get(i).isEvaluation()){
                list.add(all.get(i));
                LogUtils.i("1"+list.size());
            }
        }
        if (orderAdapter==null){
            orderAdapter = new OrderAdapter(list);
            orderRy.setAdapter(orderAdapter);
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (orderAdapter!=null){
            List<PaymentBean> list1 = new ArrayList<>();
            orderAdapter.getData().clear();
            paymentDao = PaymentDataBean.getInstance(getContext()).getPaymentDao();
            all = paymentDao.findAll();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).isReceipt()&&all.get(i).isEvaluation()){
                    list1.add(all.get(i));

                }
            }
            orderAdapter.getData().addAll(list1);
            orderAdapter.notifyDataSetChanged();

        }
    }
}
