package com.wpz.mymvpframe.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wpz on 2017/5/17.
 *
 * 定义两个方法  一个绑定view  一个解除绑定
 */

public class BasePresenter<T extends BaseView> {

    private T mT;
    public CompositeDisposable compositeDisposable;

    public void attachView(T t){
        this.mT=t;
//        AlwaysThere();
    }
    public T getView(){
        return mT;
    }

    public void detachView() {
        if (mT != null) {
            mT = null;
            clearDisposable();
        }
    }


    //添加订阅 统一管理,利用rxbinding类库提供的方法处理控件监听事件，如RxView
    public void addDisposables(Disposable disposable){

        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    //销毁订阅 统一管理
    public void clearDisposable(){
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }

    }

}
