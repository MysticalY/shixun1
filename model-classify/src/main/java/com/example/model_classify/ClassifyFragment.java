package com.example.model_classify;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.library_base.view.BaseFragment;
import com.example.library_community.ARouterPath;
import com.example.model_classify.adapter.ClassTextAdapter;
import com.example.model_classify.adapter.GoodsAdapter;
import com.example.model_classify.bean.ClassTextBean;
import com.example.model_classify.bean.GoodsBean;
import com.example.model_classify.contract.ClassContract;
import com.example.model_classify.model.ClassModel;
import com.example.model_classify.presenter.ClassPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:19:49
 * author:yanghaoyang
 */
@Route(path = ARouterPath.classify.PAGER_CLASSIFY)
public class ClassifyFragment extends BaseFragment<ClassPresenter> implements ClassContract.ClassView{
    private RecyclerView classText;
    private ClassTextAdapter adapter;
    private List<ClassTextBean.DataBean> textBean;
    private RecyclerView goodsRy;
    private GoodsAdapter goodsAdapter;
    private List<GoodsBean.DataBean> goodsBean;
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    @Override
    public int bindLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initView() {

        classText = (RecyclerView) findViewById(R.id.class_text);
        pFragment = new ClassPresenter(new ClassModel(), this);
        classText.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsRy = (RecyclerView) findViewById(R.id.goods_ry);
        goodsRy.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void initData() {
        pFragment.getClassText();
        pFragment.getGoodsBean("女装/女士精品","女装/女士精品");
    }

    @Override
    public void getClassText(ClassTextBean bean) {
        textBean = bean.getData();
        if (adapter == null) {
            adapter = new ClassTextAdapter(textBean);
            classText.setAdapter(adapter);
//            View viewByPosition = adapter.getViewByPosition(0, R.id.class_layout);
//            assert viewByPosition !=null;
//            TextView viewById = viewByPosition.findViewById(R.id.text_item);
//            viewById.setTextColor(Color.GRAY);
        }
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                for (int i = 0; i < textBean.size(); i++) {
                    View viewByPosition = adapter.getViewByPosition(i, R.id.class_layout);
                    assert viewByPosition !=null;
                    TextView viewById = viewByPosition.findViewById(R.id.text_item);
                    viewById.setTextColor(Color.GRAY);
                    if (i==position){
                        View byPosition = adapter.getViewByPosition(i, R.id.class_layout);
                        assert byPosition !=null;
                        TextView textView = byPosition.findViewById(R.id.text_item);
                        textView.setTextColor(Color.RED);
                        if (isFastClick()){
                            pFragment.getGoodsBean(textBean.get(i).getCategory_name(),textBean.get(i).getCategory_name());
                        }


                    }
                }
            }
        });
    }

    @Override
    public void getGoods(GoodsBean bean) {
        goodsBean = bean.getData();
        if (goodsAdapter==null){
            goodsAdapter = new GoodsAdapter(goodsBean);
            goodsRy.setAdapter(goodsAdapter);
            goodsAdapter.notifyDataSetChanged();
        }else {
            goodsAdapter.getData().clear();
            goodsAdapter.getData().addAll(goodsBean);
            goodsAdapter.notifyDataSetChanged();
        }
        goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

            }
        });

    }
    public  boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;

        return flag;
    }
}
