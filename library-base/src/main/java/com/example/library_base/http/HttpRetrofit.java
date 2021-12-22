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
                               Request  request = chain.request().newBuilder().header("Authorization", "bearer IWuVgJ-RmHD9lKetcJyI4GeswDhX9Urm0naiIGpfG_I0Jba4Ws6NH_0nydGDMdjRYo29HtAoteREjlrg05UAWIlXYFRtaAbZj1mtzhAkFKC8jRKsMIBlLilZycQSdNdFu5VWU2ekbnNggh2GVcqw-AZuR5kgJI9Tn9xJDNfdckFNbZTzwNatx4IYrXwssn7pvugPeFh4twKTLBImPfn5X1WXAHEo6qsOWliuaBQUUtqRVHuVT_lS1kkSh0J9i4qBoOEoAqQ0i__WRw2DamuK3TFnWdZR1hlbtG9Zby4jGA4")
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
