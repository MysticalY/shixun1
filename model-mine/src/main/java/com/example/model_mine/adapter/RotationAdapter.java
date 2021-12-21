package com.example.model_mine.adapter;

import com.example.model_mine.R;
import com.example.model_mine.bean.RotationBean;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:10:50
 * author:YangHaoYang
 * Package com.example.model_mine.adapter
 */
public class RotationAdapter extends BaseBannerAdapter<RotationBean> {
    @Override
    protected void bindData(BaseViewHolder<RotationBean> holder, RotationBean data, int position, int pageSize) {
        holder.setImageResource(R.id.rotation_img,data.getImageRes());
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_custom_view;
    }
}
