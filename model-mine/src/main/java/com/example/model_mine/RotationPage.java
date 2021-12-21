package com.example.model_mine;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.library_base.view.BaseActivity;
import com.example.library_community.util.LogUtils;
import com.example.model_mine.adapter.RotationAdapter;
import com.example.model_mine.bean.RotationBean;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.indicator.enums.IndicatorSlideMode;

import java.util.ArrayList;
import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:10:04
 * author:YangHaoYang
 * Package com.example.model_mine
 */
public class RotationPage extends BaseActivity {
    private BannerViewPager rotationBanner;
    private TextView textBanner;
    private TextView textBut;
    private String[] strings = new String[]{"在这里\n你可以买到你想买的东西", "在这里\n购物是心情愉快的选择", "在这里\n不再错过可以改变你一生的商品"};
    private List<RotationBean> data;

    @Override
    public int bindLayout() {
        return R.layout.rotationpage_item;
    }

    @Override
    public void initView() {

        rotationBanner =  findViewById(R.id.rotation_banner);
        textBanner = findViewById(R.id.text_banner);
        textBut =  findViewById(R.id.text_but);
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();

    }

    @Override
    public void initData() {
        data = addDrawable();
        setupViewPager();
        textBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RotationPage.this,MineActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setupViewPager(){
        rotationBanner.setCanLoop(false)
                .setPageTransformer(new RotateUpTransformer())
                .setIndicatorMargin(0,0,0, (int) getResources().getDimension(R.dimen.dp_100))
                .setIndicatorSliderGap((int) getResources().getDimension(R.dimen.dp_10))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorSliderRadius((int) getResources().getDimension(R.dimen.dp_3),(int)getResources().getDimension(R.dimen.dp_4_5))
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        updateUI(position);
                        LogUtils.i(position+"");
                    }
                })
        .setAdapter(new RotationAdapter())
        .setIndicatorSliderColor(Color.BLACK, ContextCompat.getColor(this,R.color.black))
        .create(data);
        ;
    }
    private void updateUI(int position ) {
        textBanner.setText(strings[position]);
        ObjectAnimator translationX  = ObjectAnimator.ofFloat(textBanner, "translationX", -120f, 0f);
        translationX.setDuration(1300).setInterpolator(new DecelerateInterpolator());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(textBanner,"alpha",0f,1f);
        alpha.setDuration(1300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX,alpha);
        animatorSet.start();
        if (position==rotationBanner.getData().size()-1&&textBut.getVisibility() == View.GONE){
            textBut.setVisibility(View.VISIBLE);
            LogUtils.i("222");
            ObjectAnimator alpha1  = ObjectAnimator.ofFloat(textBut, "alpha", 0, 1);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(textBut, "translationY", 120f, 0f);
            alpha1.setDuration(1300);
            objectAnimator1.setDuration(1300);
            objectAnimator1.setInterpolator(new DecelerateInterpolator());
            AnimatorSet animatorSet1 = new AnimatorSet();
            animatorSet1.playTogether(objectAnimator1,alpha1);
            animatorSet1.start();
        }else {
            LogUtils.i("111");
            textBut.setVisibility(View.GONE);
        }
    }
    public List<RotationBean> addDrawable(){
        List<RotationBean> list = new ArrayList<>();
        RotationBean rotationBean = new RotationBean(R.drawable.guide0, strings[0]);
        list.add(rotationBean);
        RotationBean rotationBean2 = new RotationBean(R.drawable.guide1, strings[1]);
        list.add(rotationBean2);
        RotationBean rotationBean3 = new RotationBean(R.drawable.guide2, strings[2]);
        list.add(rotationBean3);
        return list;
    }
}
