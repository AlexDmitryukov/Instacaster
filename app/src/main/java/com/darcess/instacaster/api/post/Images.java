
package com.darcess.instacaster.api.post;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Images {

    @SerializedName("standard_resolution")
    private StandardResolution mStandardResolution;

    public StandardResolution getStandardResolution() {
        return mStandardResolution;
    }

    public void setStandardResolution(StandardResolution standardResolution) {
        mStandardResolution = standardResolution;
    }

}
