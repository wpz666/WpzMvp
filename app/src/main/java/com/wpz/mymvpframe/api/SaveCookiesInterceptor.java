package com.wpz.mymvpframe.api;

import android.content.Context;

import com.wpz.mymvpframe.application.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wpz on 2017/10/24 0024.
 * 类作用：
 */

public class SaveCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }

                MyApplication.getInstance().getSharedPreferences("cookie", Context.MODE_PRIVATE).edit().putStringSet("cookies",cookies).apply();

            }


        }

        return originalResponse;
    }
}


