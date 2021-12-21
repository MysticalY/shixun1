package com.example.model_shopping;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.addressdb.AddressBean;
import com.example.library_community.addressdb.AddressDao;
import com.example.library_community.addressdb.AddressDataBase;
import com.example.model_shopping.adapter.AddressAdapter;
import com.example.model_shopping.ui.MassagerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/18
 * Time:15:59
 * author:YangHaoYang
 * Package com.example.model_shopping
 */
public class AddressActivity extends BaseActivity {
    private RecyclerView addressRy;
    private Button addressBut;
    private AddressAdapter addressAdapter;
    private AddressDao addressDao;
    private List<AddressBean> all;

    @Override
    public int bindLayout() {
        return R.layout.address;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        addressRy =  findViewById(R.id.address_ry);
        addressBut =  findViewById(R.id.address_but);
        addressDao = AddressDataBase.getInstance(this).getAddressDao();
        addressRy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {
        all = addressDao.findAll();
        if (addressAdapter==null){
            addressAdapter = new AddressAdapter(all);
            addressRy.setAdapter(addressAdapter);
        }

        addressBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressActivity.this, MassagerActivity.class);
                startActivity(intent);
            }
        });
        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                AddressBean bean = all.get(position);
                EventBus.getDefault().postSticky(bean);
                finish();
            }
        });
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void massager(String msg){
        if (msg.equals("massager")){
            all = addressDao.findAll();
            addressAdapter.getData().clear();
            addressAdapter.getData().addAll(all);
            addressAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
