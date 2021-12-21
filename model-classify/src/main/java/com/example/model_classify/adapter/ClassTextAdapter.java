package com.example.model_classify.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.model_classify.R;
import com.example.model_classify.bean.ClassTextBean;

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
 * Time:8:40
 * author:YangHaoYang
 * Package com.example.model_classify.adapter
 */
public class ClassTextAdapter extends BaseQuickAdapter<ClassTextBean.DataBean, BaseViewHolder> {
    int index = 0;
    public ClassTextAdapter( @Nullable List<ClassTextBean.DataBean> data) {
        super(R.layout.classtext_item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ClassTextBean.DataBean dataBean) {
        TextView view = baseViewHolder.getView(R.id.text_item);
        if (index==0){
            view.setTextColor(Color.RED);
            index++;
        }
        baseViewHolder.setText(R.id.text_item,""+dataBean.getCategory_name());
    }
}
