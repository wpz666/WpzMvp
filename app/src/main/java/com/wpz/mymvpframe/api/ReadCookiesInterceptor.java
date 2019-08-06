package com.wpz.mymvpframe.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.wpz.mymvpframe.application.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @类作用:
 * @author: 王鹏智
 * @Date: 2017/10/24  20:23
 * <p>
 * 思路：
 */


public class ReadCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        HashSet<String> preferences=(HashSet<String>)sp.getStringSet("cookies",new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.e("OkHttp", "Adding Header: " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
