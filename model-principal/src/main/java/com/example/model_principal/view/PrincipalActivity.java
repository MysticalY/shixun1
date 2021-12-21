package com.example.model_principal.view;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehenjoom.redspot.RedSpot;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.shappingdb.ShoppingBean;
import com.example.library_community.shappingdb.ShoppingDao;
import com.example.library_community.shappingdb.ShoppingDataBase;
import com.example.library_community.util.GlideUtil;
import com.example.model_principal.R;
import com.example.model_principal.bean.GroomBean;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/15
 * Time:13:45
 * author:YangHaoYang
 * Package com.example.model_principal.view
 */
public class PrincipalActivity extends BaseActivity {
    private TextView ppFinish;
    private TextView principalName;
    private ImageView principalImg;
    private TextView principalText;
    private Button principalBut;
    private ShoppingDao shoppingDao;
    private ShoppingBean shoppingBean;
    private TextView principalShipping;
    private RedSpot principalNum;
    private Thread thread;

    @Override
    public int bindLayout() {
        return R.layout.activity_principal;
    }

    @Override
    public void initView() {

        ppFinish =  findViewById(R.id.pp_finish);
        principalName =  findViewById(R.id.principal_name);
        principalImg =  findViewById(R.id.principal_img);
        principalText =  findViewById(R.id.principal_text);
        principalBut = findViewById(R.id.principal_but);
        principalShipping = (TextView) findViewById(R.id.principal_shipping);
        principalNum =  findViewById(R.id.principal_num);
        shoppingDao = ShoppingDataBase.getInstance(this).getShoppingDao();
        shoppingBean = new ShoppingBean();
    }

    @Override
    public void initData() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        myFinish();
        myNum();

        Intent intent = getIntent();

        GroomBean.DataBean groom = (GroomBean.DataBean) intent.getSerializableExtra("groom");
        principalName.setText(""+groom.getCategoryName());
        principalText.setText(""+groom.getTitle());
        GlideUtil.LoadImage(this,groom.getPictUrl(),principalImg);

        principalBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingBean.setTitle(groom.getTitle());
                shoppingBean.setPicUrl(groom.getPictUrl());
                shoppingBean.setMoney((groom.getReservePrice()));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        shoppingDao.inserts(shoppingBean);
                        myNum();
                    }
                }).start();

            }
        });
        principalShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().postSticky("1");
                finish();
            }
        });



    }
    private void  myNum()   {
      new Thread(new Runnable() {
            @Override
            public void run() {
                List<ShoppingBean> all = shoppingDao.findAll();
                if (all.size()>=0){
                    principalNum.setVisibility(View.VISIBLE);
                    principalNum.setText(String.valueOf(all.size()))
                            .setTextColor("#000000")
                            .setTextSize(15)
                            .setBackColor("#ff0000");
                    principalNum.postInvalidate();
                }else {
                    principalNum.setVisibility(View.INVISIBLE);
                    principalNum.setText(String.valueOf(all.size()));
                    principalNum.postInvalidate();
                }

            }
        }).start();




    }

    private void myFinish() {
        //退出页面
        ppFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
