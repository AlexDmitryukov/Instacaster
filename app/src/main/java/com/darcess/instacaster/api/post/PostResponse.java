
package com.darcess.instacaster.api.post;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("data")
    private List<Datum> mData;

    @SerializedName("meta")
    private Meta mMeta;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta mMeta) {
        this.mMeta = mMeta;
    }

}
