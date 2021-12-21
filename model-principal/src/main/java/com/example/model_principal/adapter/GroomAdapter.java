package com.example.model_principal.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model_principal.R;
import com.example.model_principal.bean.GroomBean;

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
 * Date:2021/12/13
 * Time:14:32
 * author:YangHaoYang
 * Package com.example.model_principal.adapter
 */
public class GroomAdapter extends BaseQuickAdapter<GroomBean.DataBean, BaseViewHolder> {
    public GroomAdapter(@Nullable List<GroomBean.DataBean> data) {
        super(R.layout.groom_item, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GroomBean.DataBean dataBean) {
        ImageView view = baseViewHolder.getView(R.id.groom_img);
        Glide.with(getContext()).load(dataBean.getPictUrl()).into(view);
        baseViewHolder.setText(R.id.groom_text,""+dataBean.getTitle());
    }
}
