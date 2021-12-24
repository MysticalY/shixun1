package com.example.model_shopping;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterActivityPath;
import com.example.library_community.ARouterPath;
import com.example.library_community.addressdb.AddressBean;
import com.example.library_community.shappingdb.ShoppingBean;
import com.example.library_community.shappingdb.ShoppingDao;
import com.example.library_community.shappingdb.ShoppingDataBase;
import com.example.library_community.util.SpUtil;
import com.example.model_shopping.adapter.AddressAdapter;
import com.example.model_shopping.adapter.ShoppingAdapter;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:15:34
 * author:yanghaoyang
 */
@Route(path = ARouterPath.shopping.PAGER_SHOPPING)
public class ShoppingFragment extends BaseFragment {
    private RecyclerView shopRv;
    private CheckBox shopCheckbox;
    private TextView shopPrice;
    private Button payBtn;
    private ShoppingAdapter adapter;
    private ShoppingDao shoppingDao;
    private List<ShoppingBean> all;
    private int num = 1;
    private Float total;
    private List<ShoppingBean> list;

    private Toolbar shopTb;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initView() {

        shopRv = (RecyclerView) findViewById(R.id.shop_rv);
        shopCheckbox = (CheckBox) findViewById(R.id.shop_checkbox);
        shopPrice = (TextView) findViewById(R.id.shop_price);
        payBtn = (Button) findViewById(R.id.pay_btn);
        shoppingDao = ShoppingDataBase.getInstance(getContext().getApplicationContext()).getShoppingDao();
        all = shoppingDao.findAll();
        shopTb = (Toolbar) findViewById(R.id.shop_tb);
        getActivity().setActionBar(shopTb);
    }

    @Override
    public void initData() {

        shopRv.setLayoutManager(new LinearLayoutManager(getContext()));

        shopRv.addItemDecoration(new Y_DividerItemDecoration(getContext()) {
            @Override
            public Y_Divider getDivider(int itemPosition) {
                Y_Divider divider = null;
                divider = new Y_DividerBuilder().setBottomSideLine(true, Color.parseColor("#E3E4E7"), 2, 0, 0).create();
                return divider;
            }
        });
        getShopping();

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = new ArrayList<>();
                for (int i = 0; i < adapter.getData().size(); i++) {
                    if (adapter.getData().get(i).isEstimate()) {
                        list.add(adapter.getData().get(i));
                    }
                }
                ARouter.getInstance().build(ARouterActivityPath.Pay.PAY_HOME).withSerializable("pay", (Serializable) list).navigation();
            }
        });

        for (int i = 0; i < adapter.getData().size(); i++) {
            if (adapter.getData().get(i).isEstimate()) {
                ShoppingBean shoppingBean = shoppingDao.find(adapter.getData().get(i).getId());
                shoppingDao.deletes(shoppingBean);
                adapter.getData().remove(i);
            }
        }
        adapter.notifyDataSetChanged();

    }

    public void getShopping() {
        adapter = new ShoppingAdapter(all);
        shopRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                if (view.getId() == R.id.shopping_cb) {
                    CheckBox checkBox = view.findViewById(R.id.shopping_cb);
                    if (checkBox.isChecked()) {
                        all.get(position).setEstimate(true);
                        int a = 0;
                        for (int i = 0; i < all.size(); i++) {
                            if (all.get(i).isEstimate()) {
                                a++;
                            }
                        }
                        if (a == all.size()) {
                            shopCheckbox.setChecked(true);
                        }
                        sum();

                    } else {
                        all.get(position).setEstimate(false);
                        int a = 0;
                        for (int i = 0; i < all.size(); i++) {
                            if (all.get(i).isEstimate()) {
                                a++;
                            }
                        }
                        if (a != all.size()) {
                            shopCheckbox.setChecked(false);
                        }
                        sum();
                    }

                } else if (view.getId() == R.id.shopping_add) {
                    int number = all.get(position).getNumber();
                    ShoppingBean shoppingBean = shoppingDao.find(all.get(position).getId());
                    shoppingBean.setNumber(++number);
                    shoppingDao.upDatas(shoppingBean);
                    all.get(position).setNumber(number);
                    sum();
                } else if (view.getId() == R.id.shopping_minus) {
                    int number = all.get(position).getNumber();
                    if (number > 1) {
                        ShoppingBean shoppingBean = shoppingDao.find(all.get(position).getId());
                        shoppingBean.setNumber(--number);
                        shoppingDao.upDatas(shoppingBean);
                        all.get(position).setNumber(number);
                        sum();
                    } else {
                        Toast.makeText(getContext(), "不能小于0", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        shopCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopCheckbox.isChecked()) {
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        adapter.getData().get(i).setEstimate(true);
                    }
                } else {
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        adapter.getData().get(i).setEstimate(false);
                    }
                }
                sum();
            }


        });
    }

    private void sum() {
        total = 0f;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).isEstimate()) {
                total += (all.get(i).getNumber() * Float.parseFloat(all.get(i).getMoney()));
            }
        }
        shopPrice.setText(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
//        SpUtil.getInstance().put(getContext(),"login",false,"login.db");

        if (adapter!=null){
                    adapter.getData().clear();
                    shoppingDao = ShoppingDataBase.getInstance(getContext().getApplicationContext()).getShoppingDao();
                    all = shoppingDao.findAll();
                    adapter.getData().addAll(all);
                    adapter.notifyDataSetChanged();

        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void notifyAdapter(String msg) {
        if (msg.equals("ntf")) {

            if (adapter != null) {
                List<ShoppingBean> all = shoppingDao.findAll();
                adapter.getData().clear();
                adapter.getData().addAll(all);
                adapter.notifyDataSetChanged();
            }

        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getShopping(String msg){
        list = new ArrayList<>();
        if (msg.equals("pay")){
            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).isEstimate()){
                    list.add(adapter.getData().get(i));
                    ShoppingBean shoppingBean = shoppingDao.find(adapter.getData().get(i).getId());
                    shoppingDao.deletes(shoppingBean);
                }

            }
            EventBus.getDefault().postSticky(list);
            List<ShoppingBean> all = shoppingDao.findAll();
            adapter.getData().clear();
            adapter.getData().addAll(all);
            adapter.notifyDataSetChanged();
            if (shopCheckbox.isChecked()){
                shopCheckbox.setChecked(false);
            }
            shopPrice.setText(String.valueOf(0.0));

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
