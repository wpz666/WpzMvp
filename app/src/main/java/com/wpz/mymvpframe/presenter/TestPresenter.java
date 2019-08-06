package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.MainBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.ITestView;

/**
 * Created by wpz on 2017/5/17.
 */

public class TestPresenter extends BasePresenter<ITestView>{

    //请求数据
    public void getMainData(Context context){
        HttpUtils.getData(RetrofitApi.getServer().getData(),new ObserverApi<MainBean>(context,this) {
            @Override
            public void onSuccess(MainBean o) {
                getView().onsuccess(o);
            }
        });
    }

}
