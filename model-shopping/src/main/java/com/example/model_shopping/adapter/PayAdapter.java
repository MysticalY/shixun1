package com.example.model_shopping.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library_community.shappingdb.ShoppingBean;
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
 * Time:8:01
 * author:YangHaoYang
 * Package com.example.model_shopping.adapter
 */
public class PayAdapter extends BaseQuickAdapter<ShoppingBean, BaseViewHolder> {
    public PayAdapter( @Nullable List<ShoppingBean> data) {
        super(R.layout.pay_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingBean shoppingBean) {
        GlideUtil.LoadImage(getContext().getApplicationContext(),shoppingBean.getPicUrl(),baseViewHolder.getView(R.id.pay_img));
        baseViewHolder.setText(R.id.pay_name,shoppingBean.getTitle());
        baseViewHolder.setText(R.id.pay_num,""+shoppingBean.getNumber());
        baseViewHolder.setText(R.id.pay_price,shoppingBean.getMoney());
    }
}
