package com.wpz.mymvpframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wpz.mymvpframe.application.MyApplication;

/**
 * Created by wpz on 2017/5/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected Context mContext;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        init();
        //初始化当前Activity控件
        initView();
        //初始化数据
        initDatas();

    }

    private void init(){
        mContext = this;
        //添加当前Activity
        MyApplication.getInstance().addActivity(this);
        createPresenter();
        mPresenter.attachView(this);
    }

    protected abstract void createPresenter();

    //初始化布局
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initDatas();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除当前Activity
        MyApplication.getInstance().removeActivity(this);
        //        Presenter解除绑定
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

}
