package com.example.model_classify.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library_community.util.GlideUtil;
import com.example.model_classify.R;
import com.example.model_classify.bean.GoodsBean;

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
 * Date:2021/12/14
 * Time:9:58
 * author:YangHaoYang
 * Package com.example.model_classify.adapter
 */
public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean, BaseViewHolder> {
    public GoodsAdapter( @Nullable List<GoodsBean.DataBean> data) {
        super(R.layout.goods_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.goods_text,""+dataBean.getTitle());
        ImageView view = baseViewHolder.getView(R.id.goods_img);
        GlideUtil.LoadImage(getContext(),dataBean.getPictUrl(),view);
    }
}
