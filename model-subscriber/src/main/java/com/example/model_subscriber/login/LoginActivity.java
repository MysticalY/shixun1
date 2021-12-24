package com.example.model_subscriber.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.ARouterActivityPath;
import com.example.library_community.util.LogUtils;
import com.example.library_community.util.SpUtil;
import com.example.model_subscriber.R;
import com.example.model_subscriber.bean.SubscriberBean;
import com.example.model_subscriber.contract.LoginContract;
import com.example.model_subscriber.contract.RegisterContract;
import com.example.model_subscriber.model.LoginModel;
import com.example.model_subscriber.presenter.LoginPresenter;
import com.example.model_subscriber.register.RegisterActivity;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/23
 * Time:11:19
 * author:YangHaoYang
 * Package com.example.model_subscriber.login
 */
@Route(path = ARouterActivityPath.login.LOGIN_HOME)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.SubscriberView {
    private EditText loginUsername;
    private EditText loginPassword;
    private Button loginBtn;
    private Button regBtn;
    private SubscriberBean.DataBean bean;
    private String NAME = null;
    @Override
    public int bindLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {

        loginUsername = (EditText) findViewById(R.id.login_username);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login_btn);
        regBtn = (Button) findViewById(R.id.reg_btn);
        bean = new SubscriberBean.DataBean();
        pActivity = new LoginPresenter(new LoginModel(),this);
    }

    @Override
    public void initData() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setUsername(loginUsername.getText().toString());
                bean.setPwd(loginPassword.getText().toString());
                pActivity.getLogin(bean);
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getLogin(SubscriberBean bean) {

        if (bean.getCode()==200){
            LogUtils.i("登录成功");
            SpUtil.put(LoginActivity.this,"login",false,"login.db");
            SpUtil.put(LoginActivity.this,"name",bean.getData().getUsername(),"name.xml");

            ARouter.getInstance().build(ARouterActivityPath.mine.MINE_HOME).navigation();
        }
    }
}
