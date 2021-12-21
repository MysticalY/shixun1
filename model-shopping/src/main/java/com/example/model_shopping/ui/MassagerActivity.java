package com.example.model_shopping.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.library_base.view.BaseActivity;
import com.example.library_community.addressdb.AddressBean;
import com.example.library_community.addressdb.AddressDao;
import com.example.library_community.addressdb.AddressDataBase;
import com.example.model_shopping.R;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import kotlin.ranges.IntRange;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/18
 * Time:16:30
 * author:YangHaoYang
 * Package com.example.model_shopping.ui
 */
public class MassagerActivity extends BaseActivity {
    private EditText massageName;
    private EditText massagePhone;
    private EditText massageZone;
    private Button massageBut;
    private AddressDao addressDao;

    @Override
    public int bindLayout() {
        return R.layout.massager;
    }

    @Override
    public void initView() {

        massageName = findViewById(R.id.massage_name);
        massagePhone =  findViewById(R.id.massage_phone);
        massageZone =  findViewById(R.id.massage_zone);
        massageBut = findViewById(R.id.massage_but);
    }

    @Override
    public void initData() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        addressDao = AddressDataBase.getInstance(this).getAddressDao();
        AddressBean addressBean = new AddressBean();
        massageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        addressBean.setName(massageName.getText().toString());
                        addressBean.setPhone(massagePhone.getText().toString());
                        addressBean.setZone(massageZone.getText().toString());
                        addressDao.inserts(addressBean);
                        EventBus.getDefault().postSticky("massager");
                        finish();
                    }
                }.start();
            }
        });
    }
}
