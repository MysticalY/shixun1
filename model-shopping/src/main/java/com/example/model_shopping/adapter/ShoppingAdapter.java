package com.example.model_shopping.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library_community.shappingdb.ShoppingBean;
import com.example.library_community.util.GlideUtil;
import com.example.model_shopping.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 *_ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 *__╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 *___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 *____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 *_____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 *_____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/16
 * Time:10:27
 * author:YangHaoYang
 * Package com.example.model_shopping.adapter
 */
public   class ShoppingAdapter extends BaseQuickAdapter<ShoppingBean, BaseViewHolder> {
    public ShoppingAdapter(@Nullable List<ShoppingBean> data) {
        super(R.layout.shopping_item, data);
        addChildClickViewIds(R.id.shopping_cb,R.id.shopping_add,R.id.shopping_minus);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingBean shoppingBean) {
        baseViewHolder.setText(R.id.shopping_name,shoppingBean.getTitle());
        baseViewHolder.setText(R.id.shopping_price,shoppingBean.getMoney());
        GlideUtil.LoadImage(getContext().getApplicationContext(),shoppingBean.getPicUrl(),baseViewHolder.getView(R.id.shopping_img));
        CheckBox checkBox = baseViewHolder.getView(R.id.shopping_cb);
        checkBox.setChecked(shoppingBean.isEstimate());
        baseViewHolder.setText(R.id.shopping_num,String.valueOf(shoppingBean.getNumber()));

    }
}
