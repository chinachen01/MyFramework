package com.example.chenyong.myframewrok.http;


import com.example.chenyong.myframewrok.FrameworkApplication;
import com.example.chenyong.myframewrok.utils.NetworkUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * the single instance of retrofit.
 */
public class RetrofitClient {
    private static Retrofit sRetrofit;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;
    //BaseUrl 后面 必须后缀 '/'
    private static String BaseUrl = "http://192.168.36.163/";

    private RetrofitClient() {
        //打印请求Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存目录
        Cache cache = new Cache(FrameworkApplication.mContext.getCacheDir(), 10 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //打印请求log
                .addInterceptor(interceptor)

                //stetho,可以在chrome中查看请求,需要翻墙,不好用
//                .addNetworkInterceptor(new StethoInterceptor())

                //添加UA
                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                .addInterceptor(new MockInterceptor())
                //必须是设置Cache目录
                .cache(cache)

                //走缓存，两个都要设置
//                .addInterceptor(new OnOffLineCachedInterceptor())
//                .addNetworkInterceptor(new OnOffLineCachedInterceptor())

                //失败重连
                .retryOnConnectionFailure(true)

                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)

                .build();
        sRetrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(okHttpClient)

                //baseUrl
                .baseUrl(NetworkUtil.protocol + NetworkUtil.airIp)

                //jackson转化器
                .addConverterFactory(JacksonConverterFactory.create())

                //Rxandroid
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static Retrofit getRetrofit() {
        return SingletonHolder.INSTANCE.sRetrofit;
    }
}
