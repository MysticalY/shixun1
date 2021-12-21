package com.example.model_classify;

import com.example.model_classify.bean.GoodsBean;
import com.example.model_classify.bean.ClassTextBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/13
 * Time:14:14
 * author:YangHaoYang
 * Package com.example.model_classify
 */
public interface Api {
    @GET("api/GoodsType/getRecommendTypes")
    Observable<ClassTextBean> getClassText();
    @GET("api/Goods/getGoods?&pageno=0&pagesize=10")
    Observable<GoodsBean> getGoods(@Query("keyword") String names,@Query("type")String name);
}
