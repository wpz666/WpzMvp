package com.wpz.mymvpframe.api;

import android.text.TextUtils;
import android.util.Log;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.application.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qizepu on 2017/5/17.
 *
 * 将Retrofit封装起来，返回Api
 */

public class RetrofitApi {


    private static SharedPrefsCookiePersistor prefsCookiePersistor=new SharedPrefsCookiePersistor(MyApplication.getInstance());
    private static OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(genericClient())
            .cookieJar(new PersistentCookieJar(new SetCookieCache(), prefsCookiePersistor))
            .build();
    static class RetrofitInstance {
        private static Api api = new Retrofit.Builder()
                .baseUrl(Content.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }

    public static HttpLoggingInterceptor genericClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (TextUtils.isEmpty(message)) {return;}
//                        //打印json数据
                Log.i("HttpLoggingInterceptor", "log: " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    //得到Server对象
    public static Api getServer() {
        return RetrofitInstance.api;
    }

}
