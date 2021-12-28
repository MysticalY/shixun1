package com.example.model_subscriber.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.library_base.view.BaseActivity;
import com.example.model_subscriber.R;
import com.example.model_subscriber.bean.SubscriberBean;
import com.example.model_subscriber.contract.RegisterContract;
import com.example.model_subscriber.model.RegisterModel;
import com.example.model_subscriber.presenter.RegisterPresenter;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:11:21
 * author:YangHaoYang
 * Package com.example.model_subscriber.register
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.SubscriberView {
    private EditText regNikesex;
    private EditText regUsername;
    private EditText regPassword;
    private EditText regBirthday;
    private SubscriberBean.DataBean bean;
    private Button regBtn;

    @Override
    public int bindLayout() {
        return R.layout.register_activity;
    }

    @Override
    public void initView() {

        regNikesex = (EditText) findViewById(R.id.reg_sex);
        regUsername = (EditText) findViewById(R.id.reg_username);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regBirthday = (EditText) findViewById(R.id.reg_birthday);
        regBtn = (Button) findViewById(R.id.reg_btn);
        bean = new SubscriberBean.DataBean();
        pActivity = new RegisterPresenter(new RegisterModel(),this);
    }

    @Override
    public void initData() {
//        NetworkUtils.isAvailableByPing();
//        NetworkUtils.isAvailable()
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setUsername(regUsername.getText().toString());
                bean.setPwd(regPassword.getText().toString());
                bean.setSex(regNikesex.getText().toString());
                bean.setBirthday(regBirthday.getText().toString());
                pActivity.getRegister(bean);
            }
        });

    }

    @Override
    public void getRegister(SubscriberBean bean) {
        if (bean.getCode()==200){
            ToastUtils.showShort("注册成功");

        }
    }


}
