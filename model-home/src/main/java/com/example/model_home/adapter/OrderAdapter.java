package com.example.model_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library_community.util.GlideUtil;
import com.example.model_home.R;
import com.example.model_home.paymentdb.PaymentBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/22
 * Time:19:29
 * author:YangHaoYang
 * Package com.example.model_home.adapter
 */
public class OrderAdapter extends BaseQuickAdapter<PaymentBean, BaseViewHolder> {
    public OrderAdapter(@Nullable List<PaymentBean> data) {
        super(R.layout.order_item, data);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PaymentBean paymentBean) {
        baseViewHolder.setText(R.id.order_Receiving_item_tv,paymentBean.getTitle());
        GlideUtil.LoadImage(getContext().getApplicationContext(),paymentBean.getPic(),baseViewHolder.getView(R.id.order_Receiving_item_img));

    }
}
