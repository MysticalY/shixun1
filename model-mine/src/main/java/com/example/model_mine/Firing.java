package com.example.model_mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.library_base.presenter.IPresenter;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.util.SpUtil;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:9:02
 * author:YangHaoYang
 * Package com.example.model_mine
 */
public class Firing extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();
        setContentView(R.layout.firing_item);
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();

        Boolean is = (Boolean) SpUtil.getInstance().get(Firing.this, "img", true, "wecome");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (is){
                        Intent intent =  new Intent(Firing.this,RotationPage.class);
                        startActivity(intent);
                        SpUtil.getInstance().put(Firing.this,"img",false,"wecome");
                        finish();
                    }else {
                        Intent intent1 =  new Intent(Firing.this,MineActivity.class);
                        startActivity(intent1);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
