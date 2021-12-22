package com.example.model_home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library_base.view.BaseActivity;
import com.example.library_community.util.GlideUtil;
import com.example.model_home.R;
import com.example.model_home.adapter.DeliveryAdapter;
import com.example.model_home.bean.AddressBean;
import com.example.model_home.contract.AddressContract;
import com.example.model_home.model.AddressModel;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.presenter.AddressPresenter;

import java.io.Serializable;
import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/21
 * Time:13:07
 * author:YangHaoYang
 * Package com.example.model_home.ui
 */
public class DeliveryActivity extends BaseActivity<AddressPresenter> implements AddressContract.geiAddressView {
    private android.widget.ImageView deliveryImg;
    private TextView deliveryText;
    private TextView deliveryText2;
    private RecyclerView deliveryRy;
    private DeliveryAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.delivery;
    }

    @Override
    public void initView() {


        deliveryImg =  findViewById(R.id.delivery_img);
        deliveryText = findViewById(R.id.delivery_text);
        deliveryText2 =  findViewById(R.id.delivery_text2);
        deliveryRy = findViewById(R.id.delivery_ry);
        deliveryRy.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {

        pActivity = new AddressPresenter(new AddressModel(),this);
        PaymentBean paymentbean = (PaymentBean) getIntent().getSerializableExtra("paymentbean");
        deliveryText.setText(paymentbean.getTitle());
        deliveryText2.setText(paymentbean.getPrice());
        GlideUtil.LoadImage(this,paymentbean.getPic(),deliveryImg);
        pActivity.getAddress("0");

    }

    @Override
    public void getAddressBean(AddressBean bean) {
        List<AddressBean.DataBean> data = bean.getData();
        if (adapter==null){
            adapter = new DeliveryAdapter(data);
            deliveryRy.setAdapter(adapter);
        }

    }

}
