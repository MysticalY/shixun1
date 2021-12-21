package com.example.model_principal.adapter;

import android.widget.ImageView;

import com.example.library_community.util.GlideUtil;
import com.example.model_principal.R;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:8:22
 * author:yanghaoyang
 */
public class BannerAdapter extends BaseBannerAdapter<Integer> {


    @Override
    protected void bindData(BaseViewHolder<Integer> holder, Integer data, int position, int pageSize) {
//        Glide.with(Utils.getApp()).load(data).apply(new RequestOptions().transform(new RoundedCorners(100))).into((ImageView) holder.findViewById(R.id.BAnner_img));
//        GlideUtil.loadRadImage(Utils.getApp(),data,holder.findViewById(R.id.BAnner_img), 100,RoundedCornersTransformation.CornerType.ALL);
        ImageView viewById = holder.findViewById(R.id.BAnner_img);

        GlideUtil.loadRoundImage(viewById.getContext(), data,viewById,20);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.banneritem;
    }
}
