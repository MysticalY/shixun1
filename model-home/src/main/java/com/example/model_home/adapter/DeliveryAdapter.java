package com.example.model_home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model_home.R;
import com.example.model_home.bean.AddressBean;

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
 * Date:2021/12/21
 * Time:14:09
 * author:YangHaoYang
 * Package com.example.model_home.adapter
 */
public class DeliveryAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {

    public DeliveryAdapter(@Nullable List<AddressBean.DataBean> data) {
        super(R.layout.delivery_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AddressBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.delivery_time,""+dataBean.getDisplaytime());
        baseViewHolder.setText(R.id.delivery_name,""+dataBean.getDescription());
    }
}
