
package com.darcess.instacaster.api.post;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("caption")
    private Caption mCaption;
    @SerializedName("created_time")
    private String mCreatedTime;
    @SerializedName("images")
    private Images mImages;
    @SerializedName("link")
    private String mLink;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("type")
    private String mType;
    @SerializedName("user")
    private User mUser;

    public Caption getCaption() {
        return mCaption;
    }

    public void setCaption(Caption caption) {
        mCaption = caption;
    }

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
    }

    public Images getImages() {
        return mImages;
    }

    public void setImages(Images images) {
        mImages = images;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
