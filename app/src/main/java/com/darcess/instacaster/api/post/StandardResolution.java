
package com.darcess.instacaster.api.post;

import com.google.gson.annotations.SerializedName;

public class StandardResolution {

    @SerializedName("url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
