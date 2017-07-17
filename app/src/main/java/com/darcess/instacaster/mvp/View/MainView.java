package com.darcess.instacaster.mvp.View;

import com.darcess.instacaster.Base.BaseView;
import com.darcess.instacaster.api.post.PostResponse;
import com.darcess.instacaster.mvp.Model.dbPost;

import java.util.List;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public interface MainView extends BaseView {
    void showPosts(List<dbPost> response);
    void updateLocation(String location);
    String getRadius();
    void setRadius(int radius);

    void showMessage(String message);
    void hideDialog();
    void showToast(String message);
    void hideToast();
}
