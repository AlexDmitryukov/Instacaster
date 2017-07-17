package com.darcess.instacaster.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darcess.instacaster.R;
import com.darcess.instacaster.api.post.PostResponse;
import com.darcess.instacaster.di.DaggerMainComponent;
import com.darcess.instacaster.di.MainModule;
import com.darcess.instacaster.Base.BaseActivity;
import com.darcess.instacaster.mvp.Presenter.MainPresenter;
import com.darcess.instacaster.mvp.View.MainView;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity  implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PostAdapter mAdapter;
    private Toast toast;

    @Inject
    protected MainPresenter mPresenter;

    @BindView(R.id.postList)
    RecyclerView rvPostList;

    @BindView(R.id.locationTV)
    TextView tvLocation;

    @BindView(R.id.radiusEV)
    EditText etRadius;


    @OnClick(R.id.reloginB)
    public void onClickRelogin() {
        mPresenter.reloginInstagram();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.reloadB)
    public void onClickReload(){
        mPresenter.getPosts();
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent){
        super.onViewReady(savedInstanceState, intent);
        setupPostsList();
        mPresenter.initInstagram(this);
    }

    private void setupPostsList() {
        rvPostList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new PostAdapter(getLayoutInflater());
        rvPostList.setAdapter(mAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDependencies(){
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void showPosts(PostResponse response) {
        mAdapter.updateList(response.getData());
    }

    @Override
    public void updateLocation(String location) {
        tvLocation.setText(location);
    }

    @Override
    public String getRadius(){
        return etRadius.getText().toString();
    }

    @Override
    public void setRadius(int radius){
        etRadius.setText(String.valueOf(radius));
    }

    @Override
    public void showMessage(String message) {
        showProgressDialog(message);
    }

    @Override
    public void hideDialog() {
        hideProgressDialog();
    }

    @Override
    public void showToast(String message) {
        hideToast();
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void hideToast(){
        if(toast!=null)
            toast.cancel();
    }
}
