package com.example.model_mine;

import android.os.Bundle;
import android.os.Looper;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.ARouterActivityPath;
import com.example.library_community.ARouterPath;
import com.example.library_community.util.SpUtil;
import com.example.model_mine.adapter.FragmentAdapters;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:53
 * author:yanghaoyang
 */
@Route(path = ARouterActivityPath.mine.MINE_HOME)
public class MineActivity  extends BaseActivity {

    private ArrayList<Fragment> list;
    private BottomBarLayout mineBb;
    private FrameLayout mineFm;
    private boolean login;
    private Boolean navigation = true;


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);

        mineBb = findViewById(R.id.mine_bb);
        mineFm =  findViewById(R.id.mine_fm);
    }



    @Override
    public void initData() {

        login = (boolean) SpUtil.getInstance().get(this, "login", true, "login.db");
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();

        list = new ArrayList<>();
        Fragment principal= (Fragment) ARouter.getInstance().build(ARouterPath.principal.PAGER_PRINCIPAl).navigation();
        Fragment classify = (Fragment) ARouter.getInstance().build(ARouterPath.classify.PAGER_CLASSIFY).navigation();
        Fragment shopping = (Fragment) ARouter.getInstance().build(ARouterPath.shopping.PAGER_SHOPPING).navigation();
        Fragment tidings  = (Fragment) ARouter.getInstance().build(ARouterPath.tidings.PAGER_TIDINGS).navigation();
        Fragment home     =   (Fragment)  ARouter.getInstance(). build(ARouterPath.Home.PAGER_HOME).navigation();
        list.add(principal);
        list.add(classify);
        list.add(shopping);
        list.add(tidings);
        list.add(home);
//        adapters = new FragmentAdapters(getSupportFragmentManager(),list);

        for (int i = 0; i < list.size(); i++) {
            getSupportFragmentManager().beginTransaction().add(R.id.mine_fm,list.get(i)).hide(list.get(i)).commit();
        }
        getSupportFragmentManager().beginTransaction().show(list.get(0)).commit();
        mineBb.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
              if (list.get(currentPosition).isAdded()){

                  if (currentPosition==2&&login){
                      ARouter.getInstance().build(ARouterActivityPath.login.LOGIN_HOME).navigation();
                  }else if (currentPosition==3&&login){
                      ARouter.getInstance().build(ARouterActivityPath.login.LOGIN_HOME).navigation();

                  }else if (currentPosition==4&&login){
                      ARouter.getInstance().build(ARouterActivityPath.login.LOGIN_HOME).navigation();

                  }else {
                      getSupportFragmentManager().beginTransaction().hide(list.get(previousPosition)).show(list.get(currentPosition)).commitAllowingStateLoss();
                  }


              }else {

                      getSupportFragmentManager().beginTransaction().hide(list.get(previousPosition)).add(R.id.mine_fm,list.get(currentPosition)).commitAllowingStateLoss();

              }
            }
        });


    }
    @Subscribe(sticky = true)
    public void getvp(String msg){
//        onResume();
        if (msg.equals("1")){

            EventBus.getDefault().postSticky("ntf");
            mineBb.setCurrentItem(2);

            navigation = false;
        }

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (navigation&&login){
            for (int i = 0; i < list.size(); i++) {
                getSupportFragmentManager().beginTransaction().hide(list.get(i)).commitAllowingStateLoss();

            }
            mineBb.setCurrentItem(0);
        }else {
            navigation = true;
        }


    }
}
