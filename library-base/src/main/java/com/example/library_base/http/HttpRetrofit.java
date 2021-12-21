package com.example.library_base.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/12
 * Time:18:13
 * author:YangHaoYang
 * Package com.example.library_base.http
 */
public class HttpRetrofit {
    private static volatile  HttpRetrofit httpRetrofit = null;

    public static HttpRetrofit getInstance() {
        if (httpRetrofit==null){
            synchronized (HttpRetrofit.class){
                if (httpRetrofit==null){
                    httpRetrofit = new HttpRetrofit();
                }
            }
        }
        return httpRetrofit;
    }
    private Retrofit retrofit ;

    public Retrofit getRetrofit(String url) {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                               Request  request = chain.request().newBuilder().header("Authorization", "bearer gvcuEfVjGHw_8X5aLEt4w6HCvO9jF2wOLjiApU4zJTPap0XgV82AhuIiBc0hjyzS9i5qrVmAtgnILcLDBMBbj4Bw4ZdZVOJvbKP6hCm_P-cWOv_CkSnOfayAWGj3Ui2BiHa8nJgnIbybc-CeNHKwx5aK0Bdjr3QrSoatkBYLrpfoKFabpzA4ijrZYJQ9ZgQFdNuxNIHuKsRBlm-7ELKd8HW9Ex51-_MQr0IYelW2MU8VFxPM-zOyBaKgoQbbcxEwLFeghdG5dJZuAVBsHpaYyx7x5iczZNQcon0oioS-1_E")
                                       .build();
                                Response proceed = chain.proceed(request);
                                return proceed;
                            }
                        })
                        .build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
