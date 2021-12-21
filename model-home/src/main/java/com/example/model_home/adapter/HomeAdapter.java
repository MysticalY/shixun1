package com.example.model_home.adapter;

import android.view.View;
import android.widget.ImageView;

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
 * Date:2021/12/20
 * Time:13:17
 * author:YangHaoYang
 * Package com.example.model_home.adapter
 */
public class HomeAdapter extends BaseQuickAdapter<PaymentBean, BaseViewHolder> {
    public HomeAdapter( @Nullable List<PaymentBean> data) {
        super(R.layout.home_item, data);
        addChildClickViewIds(R.id.mine_Receiving_item_look);
        addChildClickViewIds(R.id.mine_Receiving_item_affirm);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PaymentBean paymentBean) {
        ImageView view = baseViewHolder.getView(R.id.mine_Receiving_item_img);
        GlideUtil.LoadImage(getContext().getApplicationContext(),paymentBean.getPic(), view );
        baseViewHolder.setText(R.id.mine_Receiving_item_tv,paymentBean.getTitle());

    }
}
