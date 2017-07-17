package com.darcess.instacaster.mvp.Presenter;

import android.content.Context;
import android.util.Log;

import com.darcess.instacaster.Base.BasePresenter;
import com.darcess.instacaster.api.InstagramApiService;
import com.darcess.instacaster.api.post.PostResponse;
import com.darcess.instacaster.mvp.View.MainView;

import net.londatiga.android.instagram.Instagram;
import net.londatiga.android.instagram.InstagramSession;
import net.londatiga.android.instagram.InstagramUser;

import javax.inject.Inject;


import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

import static com.darcess.instacaster.util.Global.CLIENT_ID;
import static com.darcess.instacaster.util.Global.CLIENT_SECRET;
import static com.darcess.instacaster.util.Global.LATITUDE_MOCK;
import static com.darcess.instacaster.util.Global.LOCATION_ID_MOCK;
import static com.darcess.instacaster.util.Global.LONGITUDE_MOCK;
import static com.darcess.instacaster.util.Global.REDIRECT_URI;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public class MainPresenter extends BasePresenter<MainView>  implements SingleObserver<PostResponse> {

    private InstagramSession mInstagramSession;
    private Instagram mInstagram;
    private int radius;

    @Inject
    protected InstagramApiService mApiService;

    @Inject
    public MainPresenter() {
    }

    //INSTAGRAM LOGIN
    public void initInstagram(Context context) {
        mInstagram = new Instagram(context, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
        mInstagramSession = mInstagram.getSession();
        if(!isActive()){
            loginInstagram();
        } else {
            getPosts();
        }
    }

    public boolean isActive() {
        return mInstagramSession.isActive();
    }

    public void loginInstagram() {
        mInstagram.authorize(mAuthListener);
    }

    public void reloginInstagram(){
        mInstagramSession.reset();
    }

    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(InstagramUser user) {
            getPosts();
        }

        @Override
        public void onError(String error) {

        }

        @Override
        public void onCancel() {

        }
    };

    //POSTS
    public void getPosts(){
        getView().showMessage("Loading...");
        Observable<PostResponse> PostResponseObservable = mApiService.getPosts(
                LATITUDE_MOCK,
                LONGITUDE_MOCK,
                getRadius(),
                mInstagramSession.getAccessToken());
        subscribe(PostResponseObservable, this);
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onSuccess(PostResponse response) {
        if(response.getData().size()<10){
            getView().showToast("Not enough posts, expanding the search radius");
            getView().setRadius(radius*2);
            getPosts();
        } else {
            getView().hideToast();
            getView().hideDialog();
            getView().showToast("Loading complete");
            getView().showPosts(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        getView().hideDialog();
        String message;
        int code = ((HttpException) e).response().code();
        if(code == 400){
            message ="Invalid parameters";
        } else if (code == 429){
            message = "Exceeded request limits";
        } else {
            message = "Error loading";
        }
        getView().showToast(message);
    }

    //LOCATION
    public void reloadLocation(String location){
        getView().updateLocation(location);
    }

    public String getRadius(){
        radius = getView().getRadius().isEmpty()?500:Integer.parseInt(getView().getRadius());
        if(radius<500){
            radius = 500;
        } else if (radius > 5000) {
            radius = 5000;
        }
        getView().setRadius(radius);
        return String.valueOf(radius);
    }
}
