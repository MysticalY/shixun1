package com.example.model_principal.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model_principal.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/9
 * Time:14:34
 * author:yanghaoyang
 */
public class BrainAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {
    public BrainAdapter( @Nullable List<String> data) {
        super(R.layout.item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        ImageView view = baseViewHolder.getView(R.id.title_img);
        Glide.with(getContext()).load(s).into(view);
        baseViewHolder.setText(R.id.item_money,"￥2634.00");
        TextView money = baseViewHolder.getView(R.id.item_moneys);
        money.setText("$1000.00");
        money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
