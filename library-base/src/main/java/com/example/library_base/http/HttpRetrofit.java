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
                               Request  request = chain.request().newBuilder().header("Authorization", "bearer 9UWaIhblBZ_KVaf2G3lQBWZuCqkppmpiCYDPyqg2EmNjZos1d90CrlUsyBFPuJ0ZqPQcv7EDWgBiMkmDcUblu-eIGxNSOIroAUWch155hl9VwW51V9J-fIk8pDRzrBE7cNHQtabZfecPKGuEZiBKP4bfuid5IIZ8DWA_QVIQBghluUlZXKtk-tJAHg7IQtMY4U2mzdUzJAXJj_b5hXMqmfH_e5XjL02zW5ulpr-0i4E8WaE1iefwows7pmT1Y1UOYfoW9LELMuBlcynHC4RQ9Q1WeBaWoLQhFPW9kQ74bBk")
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
