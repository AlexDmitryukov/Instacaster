package com.darcess.instacaster.Base;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V mView;

    protected V getView() {
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, SingleObserver<T> observer){
        observable.subscribeOn(Schedulers.newThread())
                .singleOrError()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);

    }
}