package com.darcess.instacaster.mvp.View;

import com.darcess.instacaster.Base.BaseView;
import com.darcess.instacaster.api.post.PostResponse;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public interface MainView extends BaseView {
    void showPosts(PostResponse response);
    void updateLocation(String location);
    String getRadius();
    void setRadius(int radius);

    void showMessage(String message);
    void hideDialog();
    void showToast(String message);
    void hideToast();
}
