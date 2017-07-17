
package com.darcess.instacaster.api.post;

import com.google.gson.annotations.SerializedName;


public class Caption {

    @SerializedName("text")
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
