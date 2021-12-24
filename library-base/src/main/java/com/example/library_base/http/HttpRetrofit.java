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
                               Request  request = chain.request().newBuilder().header("Authorization", "bearer AIfbRm5G1BVNoVr2j-KMFHcGq5ZbGqfLGyE9jJNOkywP2YvbAyb-nct0LDVuO5Nuw3E0I6QlbFA1ffojCe8CYg6LcfFzthNfzha9wj2CD9NmzOgopyYE96WU6EcvKAVG2q6du7gDNHFuf77vFJhAjGL4ws9kaRgWBbe34NK7ybspfIdQyd2w1H6FqdsX3qa_Wwyw8y76kud3cyagGbS6XbfuNxTxcZBMVGkofeXB2o9iHpXSoix4dHUn67Bb0UctKlpoHAOSzUgLW7Dr2deWtyIBqkMV076PiUcq8Ab67ng")
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
