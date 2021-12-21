package com.example.library_community.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.math.RoundingMode;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/10
 * Time:9:07
 * author:yanghaoyang
 */
public class GlideUtil {
    //正常加载图片
    public static void LoadImage(Context context, Object url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
    //加载指定大小
    public static  void loadImageViewSize(Context mContext, Object url, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(url).override(width, height).into(mImageView);
    }

    //加载全角圆
    public static void loadRoundImage(Context context,Object url,ImageView imageView,int roundingRadius){
        Glide.with(context).load(url)
                .transform(new CenterInside(),new RoundedCorners(roundingRadius))
                .into(imageView);
    }
    //加载指定圆角
    public static void loadRadImage(Context context,Object url,ImageView imageView ,int rad,RoundedCornersTransformation.CornerType type ){
        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .apply(bitmapTransform(new RoundedCornersTransformation(rad,0,type)))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
                ;
        Glide.with(context).load(url).apply(requestOptions).into(imageView);

    }
    //加载圆
    public static void LoadCircleImage(Context context,Object url,ImageView imageView){
        Glide.with(context).load(url).circleCrop().into(imageView);
    }
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }
   // 设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }
//
//    //指定角圆角图片
//    fun loadRadImage(context: Context,url:String,imageView: ImageView,rad:Int,type: RoundedCornersTransformation.CornerType){
//        val request = RequestOptions()
//                .centerCrop()
//                .circleCrop()
//                .apply(bitmapTransform(RoundedCornersTransformation(rad,0,type)))
//                .priority(Priority.HIGH)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//
//        Glide.with(context)
//                .load(url)
//                .apply(request)
//                .into(imageView)
//    }
}
