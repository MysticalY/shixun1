package com.example.model_principal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterActivityPath;
import com.example.library_community.ARouterPath;
import com.example.library_community.util.GlideUtil;
import com.example.library_community.util.LogUtils;
import com.example.library_community.util.NetWorkSpeedUtils;
import com.example.library_community.util.SpUtil;
import com.example.model_principal.adapter.BannerAdapter;
import com.example.model_principal.adapter.BrainAdapter;
import com.example.model_principal.adapter.GroomAdapter;
import com.example.model_principal.bean.GroomBean;
import com.example.model_principal.contract.GroomContract;
import com.example.model_principal.model.GroomModel;
import com.example.model_principal.presenter.GroomPresenter;
import com.example.model_principal.view.DirectSeedingActivity;
import com.example.model_principal.view.PrincipalActivity;
import com.example.model_principal.view.SearchActivity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.utils.BannerUtils;

import org.jetbrains.annotations.NotNull;

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
 * Time:15:16
 * author:yanghaoyang
 */
@Route(path = ARouterPath.principal.PAGER_PRINCIPAl)
public class PrincipalFragment extends BaseFragment<GroomPresenter> implements GroomContract.GroomView, OnRefreshLoadMoreListener {
    private Banner principalBanner;
    private ArrayList<Integer> list;
    private EditText cearchEd;
    private TextBannerView afficheText;
    private List<String> textList;
    private RecyclerView brainRy;
    private List<String> imglist;
    private BrainAdapter adapter;
    private BannerViewPager banner2Bp;
    private GroomAdapter groomAdapter;
    private ArrayList<Integer> bannerImg;
    private int page = 1;
    private boolean is;
    private boolean login;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                Toast.makeText(getContext(), "" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    ;
    private TextView time;
    private RecyclerView groomRy;
    private SmartRefreshLayout groomSm;
    private TextView directseeding;

    @Override
    public int bindLayout() {
        return R.layout.fragment_principal;
    }


    @Override
    public void initView() {

        principalBanner = (Banner) findViewById(R.id.principal_banner);
        cearchEd = (EditText) findViewById(R.id.cearch_ed);
        afficheText = (TextBannerView) findViewById(R.id.affiche_text);
        brainRy = (RecyclerView) findViewById(R.id.brain_ry);
        banner2Bp = (BannerViewPager) findViewById(R.id.banner2_bp);
        time = (TextView) findViewById(R.id.time);
        groomRy = (RecyclerView) findViewById(R.id.groom_ry);
        groomSm = (SmartRefreshLayout) findViewById(R.id.groom_sm);
        pFragment = new GroomPresenter(new GroomModel(), this);
        directseeding = (TextView) findViewById(R.id.directseeding);
    }

    @Override
    public void initData() {
        groomSm.setOnRefreshLoadMoreListener(this);
        bannerImg();
        bannerText();
        imgadapter();
        banner2();
        cearchEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        //去看直播
        directseeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DirectSeedingActivity.class);
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetWorkSpeedUtils netWorkSpeedUtils = new NetWorkSpeedUtils(getContext(), handler);
                netWorkSpeedUtils.startShowNetSpeed();
            }
        });

//        int netWorkType = NetworkJudgmentUtil.getNetWorkType(getContext());
//        Toast.makeText(getContext(), "" + netWorkType, Toast.LENGTH_SHORT).show();
//        String networkOperatorName = NetworkJudgmentUtil.getNetworkOperatorName(getContext());
//        Toast.makeText(getContext(), "" + networkOperatorName, Toast.LENGTH_SHORT).show();
        pFragment.getGroom(page, 20);
        groomRy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        login = (boolean) SpUtil.getInstance().get(getContext(), "login", true, "login.db");
    }

    private void banner2() {
        bannerImg = new ArrayList<>();
        bannerImg.add(R.drawable.banner1);
        bannerImg.add(R.drawable.banner2);
        bannerImg.add(R.drawable.banner3);
        banner2Bp.setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setRevealWidth(BannerUtils.dp2px(60))//图片宽度
                .setPageMargin(BannerUtils.dp2px(30))//图片与图左右的边距
                .setLifecycleRegistry(getLifecycle()).
                setIndicatorVisibility(View.INVISIBLE)
                .setAdapter(new BannerAdapter())
                .create(bannerImg);

    }

    private void imgadapter() {
        imglist = new ArrayList<>();
        brainRy.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        String HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
        String HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
        String HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
        String HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
        String HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";
        String HOME_DISCOUNT_SIX = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
        String HOME_DISCOUNT_SEVEN = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
        imglist.add(HOME_DISCOUNT_ONE);
        imglist.add(HOME_DISCOUNT_TWO);
        imglist.add(HOME_DISCOUNT_THREE);
        imglist.add(HOME_DISCOUNT_FOUR);
        imglist.add(HOME_DISCOUNT_FIVE);
        imglist.add(HOME_DISCOUNT_SIX);
        imglist.add(HOME_DISCOUNT_SEVEN);
        LogUtils.i("1234");
        if (adapter == null) {
            adapter = new BrainAdapter(imglist);
            brainRy.setAdapter(adapter);
        }
    }

    private void bannerText() {
        textList = new ArrayList<>();
        textList.add("夏日炎炎,第一波福利还有30秒到达战场");
        textList.add("山有木兮木有枝");
        textList.add("大风起兮云飞扬");

        afficheText.setDatas(textList);
    }

    private void bannerImg() {
        list = new ArrayList<>();
        list.add(R.drawable.a1);
        list.add(R.drawable.a2);
        list.add(R.drawable.a3);
        principalBanner.setImages(list);
        principalBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideUtil.LoadImage(context, path, imageView);
            }
        });
        //设置样式
//        principalBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        principalBanner.setBannerAnimation(Transformer.Accordion);
        principalBanner.start();
    }

    @Override
    public void getGroomBean(GroomBean bean) {
        List<GroomBean.DataBean> data = bean.getData();
        LogUtils.i("112" + data.toString());
        groomSm.finishRefresh();
        groomSm.finishLoadMore();
        if (groomAdapter == null) {
            groomAdapter = new GroomAdapter(data);
            groomRy.setAdapter(groomAdapter);
            groomAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                    GroomBean.DataBean dataBean = data.get(position);
                    if (login){
                        ARouter.getInstance().build(ARouterActivityPath.login.LOGIN_HOME).navigation();
                    }else {
                        Intent intent = new Intent(getActivity(), PrincipalActivity.class);
                        intent.putExtra("groom", dataBean);
                        startActivity(intent);
                    }
                }
            });
        } else {
            if (is) {
                groomAdapter.getData().clear();
            }
            groomAdapter.getData().addAll(data);
            groomAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
        is = false;
        page++;
        pFragment.getGroom(page, 20);
    }

    @Override
    public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
        is = true;
        page = 1;
        pFragment.getGroom(page, 20);
    }


}
