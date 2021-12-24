package com.example.model_home.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.library_base.view.BaseActivity;
import com.example.model_home.R;
import com.example.model_home.paymentdb.PaymentBean;
import com.example.model_home.paymentdb.PaymentDao;
import com.example.model_home.paymentdb.PaymentDataBean;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/22
 * Time:13:04
 * author:YangHaoYang
 * Package com.example.model_home.ui
 */
public class AppraiseActivity extends BaseActivity {
    private RatingBar appraiseRating;
    private EditText appraiseText;
    private Button appraiseBut;
    private PaymentBean appraise;
    private PaymentDao paymentDao;
    private androidx.appcompat.widget.Toolbar appraiseTb;

    @Override
    public int bindLayout() {
        return R.layout.appraise;
    }

    @Override
    public void initView() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        appraiseRating =  findViewById(R.id.appraise_rating);
        appraiseText   =  findViewById(R.id.appraise_text);
        appraiseBut    =  findViewById(R.id.appraise_but);
        appraiseTb =  findViewById(R.id.appraise_tb);
        setSupportActionBar(appraiseTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appraiseTb.setNavigationIcon(R.mipmap.icon_back);
        appraiseTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        appraise = (PaymentBean) getIntent().getSerializableExtra("appraise");
        paymentDao = PaymentDataBean.getInstance(this).getPaymentDao();
        appraiseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentBean paymentBean = paymentDao.find(appraise.getId());
                paymentBean.setEvaluation(true);
                paymentDao.upDatas(paymentBean);
                EventBus.getDefault().postSticky("appraise");
            }
        });

    }
}
