package com.example.model_mine;

import android.view.View;

import com.example.library_community.BaseTransformer;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:10:59
 * author:YangHaoYang
 * Package com.example.model_mine
 */
public class RotateUpTransformer extends BaseTransformer {
    private static final float ROT_MOD = -15f;
    @Override
    protected void onTransform(View page, float position) {
        final float width = page.getWidth();
        final float rotation = ROT_MOD * position;

        page.setPivotX(width*0.5f);
        page.setPivotY(0f);
        page.setTranslationX(0f);
        page.setRotation(rotation);
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }
}
