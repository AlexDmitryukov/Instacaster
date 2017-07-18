package com.darcess.instacaster.mvp.Presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.darcess.instacaster.Base.BasePresenter;
import com.darcess.instacaster.api.InstagramApiService;
import com.darcess.instacaster.api.post.Datum;
import com.darcess.instacaster.api.post.PostResponse;
import com.darcess.instacaster.mvp.Model.Storage;
import com.darcess.instacaster.mvp.Model.dbPost;
import com.darcess.instacaster.mvp.View.MainView;
import com.darcess.instacaster.ui.MainActivity;
import com.darcess.instacaster.util.NetworkUtils;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.londatiga.android.instagram.Instagram;
import net.londatiga.android.instagram.InstagramSession;
import net.londatiga.android.instagram.InstagramUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

import static com.darcess.instacaster.util.Global.CLIENT_ID;
import static com.darcess.instacaster.util.Global.CLIENT_SECRET;
import static com.darcess.instacaster.util.Global.LATITUDE_MOCK;
import static com.darcess.instacaster.util.Global.LONGITUDE_MOCK;
import static com.darcess.instacaster.util.Global.REDIRECT_URI;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public class MainPresenter extends BasePresenter<MainView> implements SingleObserver<PostResponse> {


    private GoogleApiClient mGoogleApiClient;
    private InstagramSession mInstagramSession;
    private Instagram mInstagram;
    private int radius;
    RxPermissions rxPermissions;
    Location location;

    @Inject
    protected Context mContext;

    @Inject
    protected InstagramApiService mApiService;

    @Inject
    protected Storage mStorage;

    @Inject
    public MainPresenter() {
    }

    //INSTAGRAM LOGIN
    public void init(Context context) {

        //Init Instagram
        mInstagram = new Instagram(context, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
        mInstagramSession = mInstagram.getSession();

        //Init LocationServices
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Awareness.API)
                .build();
        mGoogleApiClient.connect();

        //Setup location
        initLocation();
        rxPermissions = new RxPermissions((Activity) context);

        //Check if loged in
        if (!isActive()) {
            loginInstagram();
        } else {
            getlocation();
        }
    }

    private void initLocation(){
        location = new Location("");
        location.setLatitude(LATITUDE_MOCK);
        location.setLongitude(LONGITUDE_MOCK);
    }

    public boolean isActive() {
        return mInstagramSession.isActive();
    }

    public void loginInstagram() {
        mInstagram.authorize(mAuthListener);
    }

    public void reloginInstagram() {
        mInstagramSession.reset();
    }

    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(InstagramUser user) {
            getlocation();
        }

        @Override
        public void onError(String error) {
        }

        @Override
        public void onCancel() {

        }
    };

    //POSTS
    public void getPosts() {
        getView().showMessage("Loading...");
        if (NetworkUtils.isOnline(mContext)) {
            loadOnlinePosts();
        } else {
            loadDbPosts();
        }
    }

    private void loadDbPosts() {
        getView().showPosts(mStorage.getSavedPosts());
        getView().hideDialog();
        getView().showToast("Loaded from local storage");
    }

    private void loadOnlinePosts() {
        Observable<PostResponse> PostResponseObservable = mApiService.getPosts(
                String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()),
                getRadius(),
                mInstagramSession.getAccessToken());
        subscribe(PostResponseObservable, this);
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onSuccess(PostResponse response) {
        if (response.getData().size() < 10 && radius < 5000) {
            getView().showToast("Not enough posts, expanding the search radius");
            getView().setRadius(radius * 2);
            loadOnlinePosts();
        } else {
            getView().hideToast();
            getView().hideDialog();
            getView().showToast("Loading complete");
            getView().showPosts(mapResponse(response.getData()));
        }
    }

    @Override
    public void onError(Throwable e) {
        getView().hideDialog();
        getView().showToast("Error loading");
    }

    public List<dbPost> mapResponse(List<Datum> datumList) {
        List<dbPost> postList = new ArrayList<>();
        for (int i = 0; i < datumList.size(); i++) {
            dbPost post = new dbPost(
                    datumList.get(i).getUsername(),
                    datumList.get(i).getText(),
                    datumList.get(i).getUserImgUrl(),
                    datumList.get(i).getPostImgUrl()
            );
            postList.add(post);
            mStorage.addPost(post);
        }
        return postList;
    }

    //LOCATION
    public void getlocation() {
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        // Always true pre-M
                        Awareness.SnapshotApi.getLocation(mGoogleApiClient)
                                .setResultCallback(locationResult -> {
                                    if (!locationResult.getStatus().isSuccess()) {
                                        Log.e("LOCATION_AWARNESS", "Could not get location.");
                                        getView().showToast("Location unavailable, showing default location...");
                                    } else {
                                        getView().showToast("Location updated");
                                        location.set(locationResult.getLocation());
                                        reloadLocation();
                                        getPosts();
                                    }
                                });
                    } else {
                        getView().showToast("Permission denied, showing default location...");
                        reloadLocation();
                        getPosts();
                    }
                });
    }

    public void reloadLocation(){
        getView().updateLocation(
                String.valueOf(location.getLatitude())
                +"/"+
                String.valueOf(location.getLongitude())
        );
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
