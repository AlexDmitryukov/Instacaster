package com.darcess.instacaster.mvp.Model;

/**
 * Created by Alexander Dmitryukov on 7/17/2017.
 */

public class dbPost {

    private String mUserName;
    private String mText;
    private String mUserImgUrl;
    private String mPostUrl;

    public dbPost(String mUserName, String mText, String mUserImgUrl, String mPostUrl) {
        this.mUserName = mUserName;
        this.mText = mText;
        this.mUserImgUrl = mUserImgUrl;
        this.mPostUrl = mPostUrl;
    }

    public String getUsername(){
        return mUserName;
    }

    public String getText(){
        return mText;
    }

    public String getPostImgUrl(){
        return mPostUrl;
    }

    public String getUserImgUrl(){
        return mUserImgUrl;
    }
}
