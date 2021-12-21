package com.example.model_shopping.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library_community.addressdb.AddressBean;
import com.example.library_community.util.GlideUtil;
import com.example.model_shopping.R;

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
 * Date:2021/12/18
 * Time:16:23
 * author:YangHaoYang
 * Package com.example.model_shopping.adapter
 */
public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
    public AddressAdapter(@Nullable List<AddressBean> data) {
        super(R.layout.address_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AddressBean addressBean) {
        baseViewHolder.setText(R.id.address_name,addressBean.getName());
        baseViewHolder.setText(R.id.address_phone,""+addressBean.getPhone());
        baseViewHolder.setText(R.id.address_zone,addressBean.getZone());
    }
}
