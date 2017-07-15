package com.darcess.instacaster.mvp.Presenter;

import android.content.Context;

import com.darcess.instacaster.Base.BasePresenter;
import com.darcess.instacaster.mvp.View.MainView;

import net.londatiga.android.instagram.Instagram;
import net.londatiga.android.instagram.InstagramSession;
import net.londatiga.android.instagram.InstagramUser;

import javax.inject.Inject;

import static com.darcess.instacaster.util.Global.CLIENT_ID;
import static com.darcess.instacaster.util.Global.CLIENT_SECRET;
import static com.darcess.instacaster.util.Global.REDIRECT_URI;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private InstagramSession mInstagramSession;
    private Instagram mInstagram;

    @Inject
    public MainPresenter() {
    }

    public void initInstagram(Context context) {
        mInstagram = new Instagram(context, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
        mInstagramSession = mInstagram.getSession();
        if(!isActive()){
            loginInstagram();
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

        }

        @Override
        public void onError(String error) {

        }

        @Override
        public void onCancel() {

        }
    };
}
