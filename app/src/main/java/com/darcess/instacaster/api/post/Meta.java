package com.darcess.instacaster.api.post;

import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("code")
    private String mCode;

    public void setCode(String code) {
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }
}
